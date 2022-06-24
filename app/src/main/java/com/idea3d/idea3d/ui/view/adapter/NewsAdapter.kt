package com.idea3d.idea3d.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.BaseViewHolder
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.databinding.RowNewsBinding


class NewsAdapter(private val context: Context, private val newsList:List<News>,
                  private val itemClickListener:OnNewsClickListener):
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnNewsClickListener{
        fun onNewsClick(news: News)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(newsList[position])
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    inner class MainViewHolder(private val itemBinding: RowNewsBinding):
        BaseViewHolder<News>(itemBinding.root) {
        override fun bind(item: News) {
            val image = "${item.urlToImage}"
            Glide.with(context)
                .load(image)
                .centerCrop()
                .placeholder(R.drawable.idea_3d_brand_2020_02)
                .dontAnimate()
                .into(itemBinding.ivPortada)
            itemBinding.tvTitulo.text = item.title
            itemBinding.tvDesc.text=item.content
            itemView.setOnClickListener {itemClickListener.onNewsClick(item)}
        }
    }
}