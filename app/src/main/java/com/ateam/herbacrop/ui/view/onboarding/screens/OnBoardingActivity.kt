package com.ateam.herbacrop.ui.view.onboarding.screens

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.viewpager2.widget.ViewPager2
import com.ateam.herbacrop.R
import com.ateam.herbacrop.databinding.ActivityOnBoardingBinding
import com.ateam.herbacrop.ui.view.activity.HomeActivity
import com.ateam.herbacrop.ui.view.onboarding.ViewPagerAdapter

class OnBoardingActivity : AppCompatActivity() {
    lateinit var viewpager: ViewPager2
    lateinit var binding : ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences: SharedPreferences = getSharedPreferences("Preferences", MODE_PRIVATE)
        val firstConditions : String? = sharedPreferences.getString("first_time", "")

        if (firstConditions.equals("Yes")){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{
            sharedPreferences.edit { putString("first_time", "Yes") }
        }

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewpager = findViewById(R.id.viewPager)
        supportActionBar?.hide()

        val fragmentList  = arrayListOf(
            FirstOnBoardingFragment(),
            SecondOnBoardingFragment(),
            ThirdOnBoardingFragment()
        )

        val adapter = ViewPagerAdapter(
           fragmentList,this)
        viewpager.adapter = adapter

            binding.indicator.setViewPager(viewpager)

        binding.buttonstart.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}