package com.idea3d.idea3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

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

    override fun onClickFragmentButton() {
        Toast.makeText(this, "El botón ha sido pulsado", Toast.LENGTH_SHORT).show()
        loadFragment(SolucionFragment())
    }

    /*override fun OnResolucionClick() {


    }*/


    /*private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }*/


}