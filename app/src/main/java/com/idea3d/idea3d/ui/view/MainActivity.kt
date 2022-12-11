package com.idea3d.idea3d.ui.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        setUpNavController()

    }


    private fun setUpNavController(){
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(bottomNavigationView, navController)


        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.main -> {
                    navController.navigate(R.id.mainFragment)
                    binding.banner.background = null
                    binding.banner.setImageDrawable(getDrawable(R.drawable.logo_largo_dos))
                    true
                }
                R.id.calcu -> {
                    navController.navigate(R.id.calcuFragment)
                    binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_idea))
                    binding.banner.setImageDrawable(getDrawable(R.drawable.logo_white_large))
                    true
                }
                R.id.guide -> {
                    navController.navigate(R.id.guideFragment)
                    binding.banner.background = null
                    binding.banner.setImageDrawable(getDrawable(R.drawable.logo_largo_dos))
                    true
                }

                else -> false
            }
        }

    }

    fun setThemeMain(){
        binding.banner.background = null
        binding.banner.setImageDrawable(getDrawable(R.drawable.logo_largo_dos))
    }

    fun setThemeCalcu(){
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_idea))
        binding.banner.setImageDrawable(getDrawable(R.drawable.logo_white_large))
    }

}



