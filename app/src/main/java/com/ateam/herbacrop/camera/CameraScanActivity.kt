@file:Suppress("DEPRECATION")

package com.ateam.herbacrop.camera

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.ateam.herbacrop.core.utils.ScanOperation
import com.ateam.herbacrop.databinding.ActivityCameraScanBinding
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraScanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraScanBinding
    private var currentPhotoPath : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCapture.setOnClickListener {
            capturePhoto()
        }

        binding.btnChoose.setOnClickListener {
            val checkSelfPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (checkSelfPermission != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            }
            else{
                val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(gallery, ScanOperation.operationChoose)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            ScanOperation.operationCapture -> {
                if (resultCode == Activity.RESULT_OK){
                    val file = File(currentPhotoPath)
                    Timber.tag("absolute Uri").d("Absolute Uri of Image is ${Uri.fromFile(file)}")

                    val mediaScan = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
                    val contentUri = Uri.fromFile(file)
                    mediaScan.data = contentUri
                    this.sendBroadcast(mediaScan)

                    val intent = Intent(this, PictureCheckActivity::class.java)
                    intent.putExtra(PictureCheckActivity.EXTRA_USERS, contentUri)
                    startActivity(intent)
                }
            }

            ScanOperation.operationChoose -> {
                if (resultCode == Activity.RESULT_OK){
                    val contentUri = data?.data
                    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                    val imageFileName = "JPEG${timeStamp}."+ contentUri?.let { getFileExt(it) }

                    val intent = Intent(this, PictureCheckActivity::class.java)
                    intent.putExtra(PictureCheckActivity.EXTRA_USERS, contentUri)
                    startActivity(intent)
                }
            }

        }

    }

    private fun getFileExt(contentUri: Uri): Any? {
        val resolver = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(resolver.getType(contentUri))
    }

    private fun capturePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 3)
        }else{
            dispatchTakePictureIntent()
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null){
            var photo : File? = null
            try {
                photo = createImageFile()
            }catch (e: IOException){
                Timber.e(e)
            }
            if (photo != null) {
                val photoUri : Uri = FileProvider.getUriForFile(this, "com.ateam.herbacrop.fileprovider", photo)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                startActivityForResult(takePictureIntent, ScanOperation.operationCapture)
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun createImageFile() : File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG${timeStamp}_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DCIM)
        val image = File.createTempFile(imageFileName,".jpg",storageDir)

        currentPhotoPath = image.absolutePath
        return image
    }


}