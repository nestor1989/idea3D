package com.idea3d.idea3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.databinding.ActivityGuiaBinding

class GuiaActivity : AppCompatActivity(), OnFragmentActionsListener {

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
        binding.recyclerGuia.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.recyclerGuia.layoutManager=LinearLayoutManager(this)
        val adapter = problemasAdapter(this, guiaErrores)
        binding.recyclerGuia.adapter = adapter

    }

    override fun onClickFragmentButton(valor:Int, boton:Int) {

        val fragment= SolucionFragment()
        val bundle = Bundle()
        bundle.putInt("valor", valor)
        bundle.putInt("boton", boton)
        fragment.arguments=bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}