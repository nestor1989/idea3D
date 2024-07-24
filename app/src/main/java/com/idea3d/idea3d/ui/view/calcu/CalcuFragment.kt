package com.idea3d.idea3d.ui.view.calcu

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.calcu.PredefinedCost
import com.idea3d.idea3d.databinding.FragmentCalcuBinding
import com.idea3d.idea3d.ui.view.main.MainActivity
import com.idea3d.idea3d.ui.view.main.dataStore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
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

        (activity as MainActivity).setThemeCalcu()
        setInit()

        return binding.root

    }

    private fun setInit(){

        lifecycleScope.launch(Dispatchers.IO) {
            getValues()?.collect(){
                withContext(Dispatchers.Main){
                    binding.kwhHint.setText(it.kwhCost)
                    binding.filamentoHint.setText(it.materialCost)
                }
            }
        }

        binding.kwhHint.addTextChangedListener {calculate()}
        binding.pesoHint.addTextChangedListener { calculate() }
        done(binding.pesoHint)
        binding.filamentoHint.addTextChangedListener {calculate()}
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

            binding.enegyCost.text= "$"+("%.2f".format(costoEnergy))
        }

        if(binding.pesoHint.text!!.isNotEmpty()&& binding.filamentoHint.text!!.isNotEmpty()) {
            costoPeso = binding.pesoHint.text.toString().toDouble()
            costoFilamento= binding.filamentoHint.text.toString().toDouble()
            costoFila=costoPeso*costoFilamento*0.001
            binding.materialCost.text = "$"+("%.2f".format(costoFila))
        }
        costoTotal=costoFila + costoEnergy
        binding.totalCost.text= "$"+costoTotal.toInt().toString()

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

    private suspend fun saveKwhValues(kwhCost: String){
        activity?.dataStore?.edit { preferences ->
            preferences[stringPreferencesKey("kwhCost")] = kwhCost
        }
    }

    private suspend fun saveMaterialValues(materialCost: String){
        activity?.dataStore?.edit { preferences ->
            preferences[stringPreferencesKey("materialCost")] = materialCost
        }
    }

    private fun getValues() = activity?.dataStore?.data?.map { preferences->
        PredefinedCost(
        kwhCost = preferences[stringPreferencesKey("kwhCost")],
        materialCost = preferences[stringPreferencesKey("materialCost")]
        )
    }

    private fun done(editDone: EditText){
        editDone.setOnEditorActionListener (TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                lifecycleScope.launch {
                    saveKwhValues(binding.kwhHint.text.toString())
                    saveMaterialValues(binding.filamentoHint.text.toString())
                }
                view?.let { activity?.hideKeyboard(it) }
                binding.scrollView.scrollTo(0, 0)

            }
            return@OnEditorActionListener true
        })

    }
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}