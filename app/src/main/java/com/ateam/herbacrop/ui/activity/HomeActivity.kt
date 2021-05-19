package com.ateam.herbacrop.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ateam.herbacrop.R
import com.ateam.herbacrop.databinding.ActivityMainBinding
import com.ateam.herbacrop.ui.fragment.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(HomeFragment())

        binding.apply {
            bottomNav.background = null
            bottomNav.menu.getItem(2).isEnabled = false

            bottomNav.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.nav_home -> replaceFragment(HomeFragment())
                    R.id.nav_search -> replaceFragment(SearchFragment())
                    R.id.nav_library -> replaceFragment(LibraryFragment())
                    R.id.nav_favorite -> replaceFragment(FavoriteFragment())
                }
                true
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val transact = supportFragmentManager.beginTransaction()
        transact.replace(R.id.fragmentContainerView, fragment)
        transact.commit()
    }

}