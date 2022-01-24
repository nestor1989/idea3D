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
    var costoEnergy:Double=0.0
    var costoHoras=0.0
    var costoKwh=0.0
    var costoFilamento=0.0
    var costoPeso=0.0
    var costoFila:Double=0.0
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

            binding.enegyCost.text= ("%.2f".format(costoEnergy))
        }

        if(binding.pesoHint.text!!.isNotEmpty()&& binding.filamentoHint.text!!.isNotEmpty()) {
            costoPeso = binding.pesoHint.text.toString().toDouble()
            costoFilamento= binding.filamentoHint.text.toString().toDouble()
            costoFila=costoPeso*costoFilamento*0.001
            binding.materialCost.text = "$"+("%.2f".format(costoFila))
        }
        costoTotal=costoFila + costoEnergy
        binding.totalCost.text=("%.2f".format(costoTotal))
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when(position){
            0->coeficiente=0.08166
            1->coeficiente=0.12
            2->coeficiente=0.11
            3->coeficiente=0.14
            4->coeficiente=0.095

        }
        calculos()

    }




}