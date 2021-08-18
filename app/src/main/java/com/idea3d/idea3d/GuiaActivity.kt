package com.idea3d.idea3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.databinding.ActivityGuiaBinding

class GuiaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGuiaBinding

    val guiaErrores: List<Problemas> = listOf(
        Problemas("hilos","sale con hilos", "retracción", "picos", "temperatura", "@drawable/logo"),
        Problemas("warping", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping2", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping3", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping4", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping5", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping6", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping7", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),
        Problemas("warping8", "nadaas", "temp0", "dndj", "jdjj", "kdkdk"),

            )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

    }

    fun initAdapter(){
        binding.recyclerGuia.layoutManager=LinearLayoutManager(this)
        val adapter = problemasAdapter(guiaErrores)
        binding.recyclerGuia.adapter = adapter

    }


}