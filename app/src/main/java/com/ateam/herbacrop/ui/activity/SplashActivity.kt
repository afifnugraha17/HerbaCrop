package com.ateam.herbacrop
ding.inflate(layoutInflater)
setContentView(splashBinding.root)

Handler(mainLooper).postDelayed({
    startActivity(Intent(this, MainActivity::class.java))

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ateam.herbacrop.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000L
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashBinding = ActivitySplashBin
            finish()
        }, splashTimeOut)
    }
}