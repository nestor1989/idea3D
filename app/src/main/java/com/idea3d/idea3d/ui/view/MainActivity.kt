package com.idea3d.idea3d.ui.view

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.datastore.preferences.preferencesDataStore
import com.idea3d.idea3d.databinding.ActivityMainBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.idea3d.idea3d.R
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCES_NAME")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Idea3D)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setThemes()
        setUpNavController()

    }


    private fun setUpNavController(){
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupWithNavController(bottomNavigationView, navController)

        bottomNavigationView.menu.getItem(2).isChecked = true

        bottomNavigationView.setOnItemSelectedListener { item ->

            when(item.itemId) {

                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.main -> {
                    navController.navigate(R.id.mainFragment)
                    true
                }
                R.id.calcu -> {
                    navController.navigate(R.id.calcuFragment)
                    true
                }
                R.id.guide -> {
                    navController.navigate(R.id.guideFragment)
                    true
                }

                R.id.works -> {
                    navController.navigate(R.id.worksFragment)
                    true
                }

                else -> false
            }
        }

    }

    fun setThemeMain(){
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_dark))
        binding.bannerLogo.setImageDrawable(getDrawable(R.drawable.logo_white_large))
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)
    }

    fun setThemeCalcu(){
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_idea))
        binding.bannerLogo.setImageDrawable(getDrawable(R.drawable.logo_white_large))
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_idea)
    }
    fun setThemeHome(){
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_dark))
        binding.bannerLogo.setImageDrawable(getDrawable(R.drawable.logo_white_large))
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)
    }

    fun setCurrentNavController(current: Int){
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.menu.getItem(current).isChecked = true
    }

    fun setThemes(){

        val darkMode = applicationContext.resources.configuration.uiMode
        Log.d("DARK_MODE", darkMode.toString())
        if (darkMode == 33) binding.switch1.isChecked = true

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

}



