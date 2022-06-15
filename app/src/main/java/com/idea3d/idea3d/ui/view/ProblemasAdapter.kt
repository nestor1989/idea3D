package com.idea3d.idea3d.ui.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.databinding.ListProblemasBinding
import com.idea3d.idea3d.data.model.Problems

class ProblemsAdapter (private val itemClickListener: OnFragmentActionsListener,
                       val guiaErrores:List<Problems>): RecyclerView.Adapter<ProblemsAdapter.ProblemasHolder>() {



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



          fun render(guiaErrores: Problems) {

            itemBinding.resolucionUno.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 1) }
            itemBinding.resolucionDos.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 2) }
            itemBinding.resolucionTres.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 3) }

            itemBinding.imagen.setOnClickListener { itemClickListener.onImageClick(bindingAdapterPosition, guiaErrores.image) }

            itemBinding.imagen.setImageResource(guiaErrores.image)

            itemBinding.problemaString.text = guiaErrores.name
            itemBinding.descripcionString.text = guiaErrores.description
              itemBinding.resolucionUno.text= guiaErrores.resOne
            if (guiaErrores.resTwo!=null) {
                itemBinding.resolucionDos.text = guiaErrores.resTwo
                itemBinding.resolucionDos.visibility=View.VISIBLE
            }else {itemBinding.resolucionDos.visibility=View.INVISIBLE
            }
              if (guiaErrores.resThree!=null) {
                  itemBinding.resolucionTres.text = guiaErrores.resThree
                  itemBinding.resolucionTres.visibility=View.VISIBLE
              }else {itemBinding.resolucionTres.visibility=View.INVISIBLE
              }

            }










        }

    }

