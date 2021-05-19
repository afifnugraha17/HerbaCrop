package com.ateam.herbacrop.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ateam.herbacrop.R
import com.ateam.herbacrop.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 5000L
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        splashBinding.avi.show()

        Handler(mainLooper).postDelayed({
            splashBinding.avi.hide()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, splashTimeOut)
    }
}