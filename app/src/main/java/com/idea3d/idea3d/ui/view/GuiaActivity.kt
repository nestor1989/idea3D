package com.idea3d.idea3d.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.*
import com.idea3d.idea3d.databinding.ActivityGuiaBinding

import com.idea3d.idea3d.data.model.problemasModel.Companion.guiaErrores


class GuiaActivity : AppCompatActivity(), OnFragmentActionsListener {

    private lateinit var binding: ActivityGuiaBinding

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

    override fun onImageClick(valor: Int, imagen:Int) {
        val fragment= SolucionFragment()
        val bundle = Bundle()
        bundle.putInt("valor", valor)
        bundle.putInt("imagen", imagen)
        fragment.arguments=bundle
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


}