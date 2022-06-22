package com.idea3d.idea3d.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.BaseViewHolder
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.databinding.RowThingsBinding

class MainAdapter(private val context: Context, private val thingList:List<Thing>,
                  private val itemClickListener:OnThingClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnThingClickListener{
        fun onThingClick(thing: Thing)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RowThingsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(thingList[position])
        }
    }

    override fun getItemCount(): Int {
        return thingList.size
    }

    inner class MainViewHolder(private val itemBinding:RowThingsBinding):
        BaseViewHolder<Thing>(itemBinding.root) {
        override fun bind(item: Thing) {
            val image = "${item.image}"
            Glide.with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.idea_3d_brand_2020_02)
                .dontAnimate()
                .into(itemBinding.ivPortada)
            itemBinding.tvtitle.text=item.name
            itemBinding.tvDesc.text=item.id.toString()
            itemView.setOnClickListener {itemClickListener.onThingClick(item)}
        }
    }
}