package com.ateam.herbacrop.ui.view.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.edit
import com.ateam.herbacrop.databinding.ActivitySplashBinding
import com.ateam.herbacrop.ui.view.onboarding.screens.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000L
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE)
        val firstConditions : String? = sharedPreferences.getString("first_time", "")

        Handler(mainLooper).postDelayed({
        if (firstConditions.equals("Yes")){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{
            sharedPreferences.edit { putString("first_time", "Yes") }
            startActivity(Intent(this, OnBoardingActivity::class.java))

        }
            finish()
        }, splashTimeOut)
    }
}