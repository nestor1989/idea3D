package com.idea3d.idea3d.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.core.BaseViewHolder
import com.idea3d.idea3d.databinding.RowPaginationBinding

class PaginationAdapter(private val context: Context, private val pages:List<Int>,
                        private val itemClickListener:OnPageClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPageClickListener{
        fun onPageClick(page: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RowPaginationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(pages[position])
        }
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    inner class MainViewHolder(private val itemBinding: RowPaginationBinding):
        BaseViewHolder<Int>(itemBinding.root) {
        override fun bind(item: Int) {
            itemBinding.buttonNumber.text=item.toString()
            itemBinding.buttonNumber.setOnClickListener {
                it.isActivated =! it.isActivated

                itemClickListener.onPageClick(item)

            }
        }
    }
}