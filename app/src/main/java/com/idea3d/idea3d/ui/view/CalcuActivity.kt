package com.idea3d.idea3d.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.idea3d.idea3d.R
import com.idea3d.idea3d.databinding.ActivityCalcuBinding


class CalcuActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    private lateinit var binding: ActivityCalcuBinding
    var coeficiente=1.0
    var costoEnergy=0.0
    var costoHoras=0.0
    var costoKwh=0.0
    var costoFilamento=0.0
    var costoPeso=0.0
    var costoFila=0.0
    var costoTotal=0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.kwhHint.setOnClickListener { calculos() }
        binding.pesoHint.setOnClickListener { calculos() }
        binding.filamentoHint.setOnClickListener { calculos() }
        binding.horasHint.setOnClickListener { calculos() }

        val materiales = resources.getStringArray(R.array.filamentos)
        val adapter = ArrayAdapter(
            this,
            R.layout.list_item,
            materiales
        )

        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
            onItemClickListener=this@CalcuActivity

        }


    }

    fun calculos(){
        if(binding.kwhHint.text!!.isNotEmpty()&& binding.horasHint.text!!.isNotEmpty()) {
            costoKwh = binding.kwhHint.text.toString().toDouble()
            costoHoras= binding.horasHint.text.toString().toDouble()
            costoEnergy=costoHoras*costoKwh*coeficiente
            binding.enegyCost.text = costoEnergy.toString()
        }

        if(binding.pesoHint.text!!.isNotEmpty()&& binding.filamentoHint.text!!.isNotEmpty()) {
            costoPeso = binding.pesoHint.text.toString().toDouble()
            costoFilamento= binding.filamentoHint.text.toString().toDouble()
            costoFila=costoPeso*costoFilamento
            binding.materialCost.text = costoFila.toString()
        }
        costoTotal=costoFila + costoEnergy
        binding.totalCost.text=costoTotal.toString()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //val item= parent?.getItemAtPosition(position)
        when(position){
            0->coeficiente=1.14
            1->coeficiente=1.0
            2->coeficiente=2.0
            3->coeficiente=3.0
            4->coeficiente=4.0

        }
        calculos()

    }




}