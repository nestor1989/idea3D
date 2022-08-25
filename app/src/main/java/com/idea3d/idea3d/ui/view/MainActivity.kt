package com.idea3d.idea3d.ui.view



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.idea3d.idea3d.databinding.ActivityMainBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController

import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import com.idea3d.idea3d.R


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Idea3D)
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
                    binding.banner.setImageDrawable(getDrawable(R.drawable.idea_logo_circ))
                    true
                }
                R.id.calcu -> {
                    navController.navigate(R.id.calcuFragment)
                    binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_idea))
                    binding.banner.setImageDrawable(getDrawable(R.drawable.idea_logo_circ))
                    true
                }
                R.id.guide -> {
                    navController.navigate(R.id.guideFragment)
                    binding.banner.background = null
                    binding.banner.setImageDrawable(getDrawable(R.drawable.idea_logo_circ))
                    true
                }

                else -> false
            }
        }

    }
}



