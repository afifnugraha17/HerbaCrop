package com.ateam.herbacrop.ui.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ateam.herbacrop.R
import com.ateam.herbacrop.camera.CameraScanActivity
import com.ateam.herbacrop.databinding.ActivityMainBinding
import com.ateam.herbacrop.favorite.FavoriteFragment
import com.ateam.herbacrop.ui.view.fragment.HomeFragment
import com.ateam.herbacrop.ui.view.fragment.LibraryFragment
import com.ateam.herbacrop.ui.view.fragment.SearchFragment

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
            fab.setOnClickListener {
                val intent = Intent(this@HomeActivity, CameraScanActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about -> {
                val mIntent = Intent(this, AboutActivity::class.java)
                startActivity(mIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun replaceFragment(fragment: Fragment){
        val transact = supportFragmentManager.beginTransaction()
        transact.replace(R.id.fragmentContainerView, fragment)
        transact.commit()
    }

}