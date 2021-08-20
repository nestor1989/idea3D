package com.idea3d.idea3d

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.databinding.ListProblemasBinding

class problemasAdapter (val guiaErrores:List<Problemas>): RecyclerView.Adapter<problemasAdapter.ProblemasHolder>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemasHolder {
        val itemBinding= ListProblemasBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ProblemasHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProblemasHolder, position: Int) {
        holder.render(guiaErrores[position])

    }

    override fun getItemCount(): Int {
        return guiaErrores.size
    }

    class ProblemasHolder(private val itemBinding: ListProblemasBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun render(guiaErrores: Problemas){
            itemBinding.problemaString.text=guiaErrores.NombreProblema
            itemBinding.descripcionString.text=guiaErrores.Desripcion
            itemBinding.resolucionUno.text=guiaErrores.Resolucion1

            itemBinding.resolucionUno.setOnClickListener { apretado() }

        }

        fun apretado(){
            itemBinding.problemaString.text="FUNCIONAAAA"
        }


    }

}