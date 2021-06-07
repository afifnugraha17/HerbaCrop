@file:Suppress("DEPRECATION")

package com.ateam.herbacrop.camera

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ateam.herbacrop.databinding.ActivityPictureCheckBinding
import com.ateam.herbacrop.ml.HerbacropModel
import com.ateam.herbacrop.ui.view.activity.HomeActivity
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import timber.log.Timber
import java.io.IOException
import java.nio.ByteBuffer

class PictureCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictureCheckBinding
    private lateinit var img : Bitmap
    private lateinit var resultText : String

    companion object {
        const val EXTRA_USERS = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.extras?.get(EXTRA_USERS) as Uri

        binding.imagePicked.setImageURI(data)

        img = MediaStore.Images.Media.getBitmap(this.contentResolver, data)

        binding.buttonAccept.setOnClickListener {
            val image = Bitmap.createScaledBitmap(img, 150, 150, true)
            try {
                val model : HerbacropModel = HerbacropModel.newInstance(applicationContext)

                val inputFeature : TensorBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)

                val imageFeature = TensorImage(DataType.FLOAT32)
                imageFeature.load(image)
                val buffer : ByteBuffer = imageFeature.buffer

                inputFeature.loadBuffer(buffer)

                val output : HerbacropModel.Outputs = model.process(inputFeature)
                val outputFeature: TensorBuffer = output.outputFeature0AsTensorBuffer

                model.close()

                when{
                    outputFeature.floatArray[0].toInt() == 1 -> resultText = "Daun Jarak"
                    outputFeature.floatArray[1].toInt() == 1 -> resultText = "Daun Pegagan"
                    outputFeature.floatArray[2].toInt() == 1 -> resultText = "Daun Sirih"
                    outputFeature.floatArray[3].toInt() == 1 -> resultText = "Kumis Kucing"
                    outputFeature.floatArray[4].toInt() == 1 -> resultText = "Lidah Buaya"
                }





                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra(ResultActivity.EXTRA_USERS, resultText)
                startActivity(intent)

            }catch (e: IOException){
                Timber.e(e)
            }



        }

        binding.buttonCancel.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}