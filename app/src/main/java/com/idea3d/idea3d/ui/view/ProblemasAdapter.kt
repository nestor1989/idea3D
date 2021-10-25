package com.idea3d.idea3d.ui.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.R
import com.idea3d.idea3d.databinding.ListProblemasBinding
import com.idea3d.idea3d.data.model.Problemas

class problemasAdapter (private val itemClickListener: OnFragmentActionsListener,
                        val guiaErrores:List<Problemas>): RecyclerView.Adapter<problemasAdapter.ProblemasHolder>() {



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

            itemBinding.resolucionUno.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 1) }
            itemBinding.resolucionDos.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 2) }
            itemBinding.resolucionTres.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 3) }

            itemBinding.imagen.setOnClickListener { itemClickListener.onImageClick(bindingAdapterPosition, guiaErrores.imagen) }

            itemBinding.imagen.setImageResource(guiaErrores.imagen)

            itemBinding.problemaString.text = guiaErrores.NombreProblema
            itemBinding.descripcionString.text = guiaErrores.Descripcion
              itemBinding.resolucionUno.text= guiaErrores.Resolucion1
            if (guiaErrores.Resolucion2!=null) {
                itemBinding.resolucionDos.text = guiaErrores.Resolucion2
                itemBinding.resolucionDos.visibility=View.VISIBLE
            }else {itemBinding.resolucionDos.visibility=View.INVISIBLE
            }
              if (guiaErrores.Resolucion3!=null) {
                  itemBinding.resolucionTres.text = guiaErrores.Resolucion3
                  itemBinding.resolucionTres.visibility=View.VISIBLE
              }else {itemBinding.resolucionTres.visibility=View.INVISIBLE
              }

            }










        }

    }

