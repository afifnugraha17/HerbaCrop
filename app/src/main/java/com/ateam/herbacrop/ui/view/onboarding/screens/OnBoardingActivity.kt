package com.ateam.herbacrop.ui.view.onboarding.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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