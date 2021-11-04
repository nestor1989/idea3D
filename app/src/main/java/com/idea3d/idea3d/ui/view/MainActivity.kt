package com.idea3d.idea3d.ui.view


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idea3d.idea3d.databinding.ActivityMainBinding
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import com.idea3d.idea3d.R


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Idea3D)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected==false){
            binding.newsBoton.visibility= View.INVISIBLE
        }

        binding.guiaBoton.setOnClickListener { goGuia() }
        binding.infoBoton.setOnClickListener { goInfo() }
        binding.calcuBoton.setOnClickListener { goCalcu() }
        binding.newsBoton.setOnClickListener { goNews() }


    }



    private fun goGuia(){
        val intent = Intent(this, GuiaActivity::class.java)
        startActivity(intent)
    }

    private fun goInfo(){
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun goCalcu(){
        val intent = Intent(this, CalcuActivity::class.java)
        startActivity(intent)
    }

    private fun goNews(){
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }



}