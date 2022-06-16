package com.idea3d.idea3d.ui.view


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idea3d.idea3d.databinding.ActivityMainBinding
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
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

        setupNavController()
        setUpNavigation()

    }


    private fun setupNavController() {
        val bottomNavigationView = binding.bottomNavigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setUpNavigation(){
        NavigationBarView.OnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_1 -> {
                    navController.navigate(R.id.mainFragment)
                    true
                }
                R.id.page_2 -> {
                    navController.navigate(R.id.calcuFragment)
                    true
                }
                R.id.page_3 -> {
                    navController.navigate(R.id.guideFragment)
                    true
                }

                else -> false
            }
        }

    }
}



