package com.idea3d.idea3d.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.core.BaseViewHolder
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.model.ThingWithCat
import com.idea3d.idea3d.databinding.RowHomeParentBinding


class ThingsParentAdapter(
    private val context: Context,
    private val thingsWithCat: List<ThingWithCat>,
    private val onClickChild : OnClickChild): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnClickChild{
        fun onClickChild(thing: Thing)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RowHomeParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(thingsWithCat[position])
        }
    }

    override fun getItemCount(): Int {
        return thingsWithCat.size
    }

    inner class MainViewHolder(private val itemBinding: RowHomeParentBinding) :
        BaseViewHolder<ThingWithCat>(itemBinding.root), ThingsChildAdapter.OnThingClickListener {
        override fun bind(item: ThingWithCat) {

            val title = translate(item.catName)
            itemBinding.contentTitle.setText(title)

            val thingsChildAdapter = ThingsChildAdapter(context, item.things, this)
            itemBinding.childRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            itemBinding.childRecyclerView.adapter = thingsChildAdapter
        }

        override fun onThingClick(thing: Thing) {
            onClickChild.onClickChild(thing)
        }
    }

    private fun translate (catName:String): String{
        var spanishCat = ""

        when(catName){
            "3D Printing" -> spanishCat = "Impresos en 3D"

            "Art" -> spanishCat = "Arte"

            "Fashion" -> spanishCat = "Moda"

            "Gadgets" -> spanishCat = "Hacks life"

            "Hobby" -> spanishCat = "Hobbies"

            "Household" -> spanishCat = "Hogar"

            "Learning" -> spanishCat = "Educación"

            "Models" -> spanishCat = "Modelos"

            "Tools" -> spanishCat = "Herramientas"

            "Toys & Games" -> spanishCat = "Juegos y Juguetes"

            else -> spanishCat = catName
        }
        return spanishCat
    }

}

