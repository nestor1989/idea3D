package com.idea3d.idea3d.ui.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.databinding.RowProblemsBinding
import com.idea3d.idea3d.data.model.Problems
import com.idea3d.idea3d.ui.view.guide.OnFragmentActionsListener

class ProblemsAdapter (
    private val itemClickListener: OnFragmentActionsListener,
    private val guiaErrores:List<Problems>
    ): RecyclerView.Adapter<ProblemsAdapter.ProblemasHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemasHolder {
        val itemBinding =
            RowProblemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProblemasHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProblemasHolder, position: Int) {
        holder.render(guiaErrores[position])

    }

    override fun getItemCount(): Int {
        return guiaErrores.size
    }

    inner class ProblemasHolder(private val itemBinding: RowProblemsBinding) :

        RecyclerView.ViewHolder(itemBinding.root) {

        fun render(guiaErrores: Problems) {

            itemBinding.resolucionUno.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 1, guiaErrores.image, guiaErrores.resOne) }
            itemBinding.resolucionDos.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 2, guiaErrores.image, guiaErrores.resTwo!!) }
            itemBinding.resolucionTres.setOnClickListener { itemClickListener.onClickFragmentButton(bindingAdapterPosition, 3, guiaErrores.image, guiaErrores.resThree!!) }

            itemBinding.imagen.setOnClickListener { itemClickListener.onImageClick(bindingAdapterPosition, guiaErrores.image, guiaErrores.name) }

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

