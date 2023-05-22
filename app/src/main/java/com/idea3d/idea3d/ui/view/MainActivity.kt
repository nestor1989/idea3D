package com.idea3d.idea3d.ui.view

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.datastore.preferences.preferencesDataStore
import com.idea3d.idea3d.databinding.ActivityMainBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.idea3d.idea3d.R
import com.idea3d.idea3d.ui.view.modals.DonateModalFragment
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by preferencesDataStore(name = "USER_PREFERENCES_NAME")

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var donateModalFragment: DonateModalFragment

    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenSplash.setKeepOnScreenCondition {false}

        setThemeMain()
        setThemes()
        setUpNavController()
        setButtons()

    }

    private fun setButtons() {
        binding.donateButton.setOnClickListener{
            donateModalFragment = DonateModalFragment()
            val donateNewInst = donateModalFragment.newInstance()
            donateNewInst.show(supportFragmentManager, "donate")
        }
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
        binding.bannerLogo.visibility = View.VISIBLE
        binding.switch1.visibility = View.VISIBLE
        binding.noBanner.visibility = View.GONE
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_dark))
        binding.bannerLogo.setImageDrawable(getDrawable(R.drawable.logo_white_large))
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)
    }

    fun setThemeCalcu(){
        binding.bannerLogo.visibility = View.VISIBLE
        binding.switch1.visibility = View.VISIBLE
        binding.noBanner.visibility = View.GONE
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_idea))
        binding.bannerLogo.setImageDrawable(getDrawable(R.drawable.logo_white_large))
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_idea)
    }
    fun setThemeHome(){
        binding.bannerLogo.visibility = View.VISIBLE
        binding.switch1.visibility = View.VISIBLE
        binding.noBanner.visibility = View.GONE
        binding.banner.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_dark))
        binding.bannerLogo.setImageDrawable(getDrawable(R.drawable.logo_white_large))
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_dark)
    }

    fun setCurrentNavController(current: Int){
        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.menu.getItem(current).isChecked = true
    }

    private fun setThemes(){

        val darkMode = applicationContext.resources.configuration.uiMode
        Log.d("DARK_MODE", darkMode.toString())
        if (darkMode == 33) binding.switch1.isChecked = true

        binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    fun setNoBanner(title: String){
        binding.bannerLogo.visibility = View.GONE
        binding.switch1.visibility = View.GONE
        binding.noBanner.visibility = View.VISIBLE
        binding.tvNoBanner.setText(title)
        binding.buttonBack.setOnClickListener {
            navController.popBackStack()
        }
    }

}



