package com.idea3d.idea3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.idea3d.idea3d.databinding.ActivityCalcuBinding
import com.idea3d.idea3d.databinding.ActivityGuiaBinding

class GuiaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuiaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}