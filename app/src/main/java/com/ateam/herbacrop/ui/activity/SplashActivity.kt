package com.ateam.herbacrop.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ateam.herbacrop.R
import com.ateam.herbacrop.databinding.ActivitySplashBinding
import com.ateam.herbacrop.ui.activity.onboarding.screens.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000L
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, OnBoardingActivity::class.java))
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
            finish()
        }, splashTimeOut)
    }
}