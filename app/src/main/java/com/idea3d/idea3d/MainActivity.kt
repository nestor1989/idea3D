package com.idea3d.idea3d

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


    }


    fun goGuia(){
        val intent = Intent(this, GuiaActivity::class.java)
        startActivity(intent)
    }

    fun goInfo(){
        val intent = Intent(this, CalcuActivity::class.java)
        startActivity(intent)
    }

    fun goCalcu(){
        val intent = Intent(this, CalcuActivity::class.java)
        startActivity(intent)
    }

}