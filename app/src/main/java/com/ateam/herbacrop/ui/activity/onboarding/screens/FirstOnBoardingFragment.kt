package com.ateam.herbacrop.ui.activity.onboarding.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.ateam.herbacrop.databinding.FragmentFirstOnboardingBinding


class FirstOnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentFirstOnboardingBinding

    lateinit var viewpager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstOnboardingBinding.inflate(inflater,container, false)
        return binding.root
    }


}