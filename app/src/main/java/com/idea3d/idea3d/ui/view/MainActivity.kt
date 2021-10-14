package com.idea3d.idea3d.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idea3d.idea3d.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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