package com.idea3d.idea3d


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.databinding.ListProblemasBinding

class problemasAdapter (private val itemClickListener:OnFragmentActionsListener,
                        val guiaErrores:List<Problemas>): RecyclerView.Adapter<problemasAdapter.ProblemasHolder>() {

    /*interface OnResolucionActionListener{
        fun OnResolucionClick()
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemasHolder {
        val itemBinding =
            ListProblemasBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProblemasHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProblemasHolder, position: Int) {
        holder.render(guiaErrores[position])

    }

    override fun getItemCount(): Int {
        return guiaErrores.size
    }

    inner class ProblemasHolder(private val itemBinding: ListProblemasBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {


        fun render(guiaErrores: Problemas) {
            itemBinding.resolucionUno.setOnClickListener { itemClickListener.onClickFragmentButton() }

            itemBinding.problemaString.text = guiaErrores.NombreProblema
            itemBinding.descripcionString.text = guiaErrores.Desripcion
            itemBinding.resolucionUno.text = guiaErrores.Resolucion1

        }

    }

}