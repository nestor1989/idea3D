package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idea3d.idea3d.data.model.Problemas


class ProblemasViewModel: ViewModel() {

    val problemasModel= MutableLiveData<Problemas>()

    fun setProblemasModel(ListaProblemas:Problemas){
        problemasModel.value= ListaProblemas
    }

}