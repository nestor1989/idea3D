package com.idea3d.idea3d

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import com.idea3d.idea3d.databinding.FragmentCalcuBinding

class CalcuFragment : Fragment(), AdapterView.OnItemClickListener {
    private var _binding: FragmentCalcuBinding? = null
    private val binding get() = _binding!!
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalcuBinding.inflate(inflater, container, false)

        setInit()

        return binding.root

    }

    private fun setInit(){
        binding.kwhHint.addTextChangedListener { calculate() }
        binding.pesoHint.addTextChangedListener { calculate() }
        binding.filamentoHint.addTextChangedListener { calculate() }
        binding.horasHint.addTextChangedListener { calculate() }

        val materiales = resources.getStringArray(R.array.filamentos)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            materiales
        )

        with(binding.autoCompleteTextView) {
            setAdapter(adapter)
            onItemClickListener=this@CalcuFragment

        }

    }

    private fun calculate(){
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
        binding.totalCost.text= costoTotal.toInt().toString()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        when(position){
            0->coeficiente=0.08166
            1->coeficiente=0.12
            2->coeficiente=0.11
            3->coeficiente=0.14
            4->coeficiente=0.095

        }
        calculate()

    }

}