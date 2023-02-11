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
            itemBinding.contentTitle.text = item.catName
            val thingsChildAdapter = ThingsChildAdapter(context, item.things, this)
            itemBinding.childRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
            itemBinding.childRecyclerView.adapter = thingsChildAdapter
        }

        override fun onThingClick(thing: Thing) {
            onClickChild.onClickChild(thing)
        }
    }

}

