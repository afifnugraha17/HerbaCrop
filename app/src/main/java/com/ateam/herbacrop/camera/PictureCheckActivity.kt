package com.ateam.herbacrop.camera

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ateam.herbacrop.databinding.ActivityPictureCheckBinding
import com.ateam.herbacrop.ml.HerbacropModel
import com.bumptech.glide.Glide
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import timber.log.Timber
import java.io.IOException
import java.nio.ByteBuffer

class PictureCheckActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPictureCheckBinding

    companion object {
        const val EXTRA_USERS = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPictureCheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.extras?.get(EXTRA_USERS) as Bitmap
        Glide.with(binding.root).load(data).into(binding.imagePicked)

        binding.buttonAccept.setOnClickListener {
            val image = Bitmap.createScaledBitmap(data, 150, 150, true)
            try {
                val model : HerbacropModel = HerbacropModel.newInstance(applicationContext)

                val inputFeature : TensorBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)

                val imageFeature : TensorImage = TensorImage(DataType.FLOAT32)
                imageFeature.load(image)
                val buffer : ByteBuffer = imageFeature.buffer

                inputFeature.loadBuffer(buffer)

                val output : HerbacropModel.Outputs = model.process(inputFeature)
                val outputFeature: TensorBuffer = output.outputFeature0AsTensorBuffer

                val resultText : String = (
                        outputFeature.floatArray[0].toString() + "\n" +
                                outputFeature.floatArray[1] + "\n" +
                                outputFeature.floatArray[2] + "\n" +
                                outputFeature.floatArray[3] + "\n" +
                                outputFeature.floatArray[4])

                println(resultText)
            }catch (e: IOException){
                Timber.e(e)
            }



        }

        binding.buttonCancel.setOnClickListener {
            val intent = Intent(this,CameraScanActivity::class.java)
            startActivity(intent)
        }
    }
}