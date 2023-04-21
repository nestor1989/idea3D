package com.idea3d.idea3d.ui.view.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.BaseViewHolder
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.RowTasksBinding
import com.idea3d.idea3d.utils.OnSwipeTouchListener

class TaskAdapter (
    private val taskList:List<Task>,
    private val onClickArrow:OnClickArrow,
    private val context: Context):
    RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnClickArrow{
        fun onClickArrow(task: Task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            RowTasksBinding.inflate(LayoutInflater.from(parent.context), parent, false)



        return MainViewHolder(itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder->holder.bind(taskList[position])
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class MainViewHolder(private val itemBinding: RowTasksBinding):
        BaseViewHolder<Task>(itemBinding.root) {
        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(Build.VERSION_CODES.O)
        override fun bind(item: Task) {
            itemBinding.tvTitle.text = item.name
            itemBinding.tvDate.text=item.description
            
            itemBinding.cvTask.setOnTouchListener (object : OnSwipeTouchListener(context) {
                @SuppressLint("ClickableViewAccessibility")
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    itemBinding.buttonArrow.setImageResource(R.drawable.ic_baseline_close)
                }
                override fun onSwipeRight() {
                    super.onSwipeRight()
                    itemBinding.buttonArrow.setImageResource(R.drawable.ic_like_in)
                }
            })


            itemBinding.tvState.text = item.prioritize.toString()
            itemBinding.cvTask.setOnClickListener {
                onClickArrow.onClickArrow(item)
            }

        }
    }




}