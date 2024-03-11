package com.idea3d.idea3d.ui.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.BaseViewHolder
import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.databinding.RowTasksBinding
import com.idea3d.idea3d.ui.view.work.WorksDetailsFragment.Companion.STATUS
import com.idea3d.idea3d.utils.OnSwipeTouchListener

class TaskAdapter (
    private val taskList:List<Task>,
    private val onClickArrow:OnClickArrow,
    private val context: Context):
    RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnClickArrow{
        fun onClickArrow(task: Task)
        fun onDelete(task: Task)
        fun onUpdate(task: Task, idStatus:Int, stringStatus: String)
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
        BaseViewHolder<Task>(itemBinding.root), AdapterView.OnItemClickListener {

        @SuppressLint("ClickableViewAccessibility")
        @RequiresApi(Build.VERSION_CODES.O)
        override fun bind(item: Task) {
            itemBinding.tvTitle.text = item.name
            itemBinding.tvClient.text=item.client
            itemBinding.listStatus.setText(item.status)
            if (item.prioritize) itemBinding.buttonUrgent.visibility = View.VISIBLE

            initArray()

            item.thing_photo?.let {
                val imageBytes = Base64.decode(item.thing_photo, Base64.DEFAULT)
                val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                itemBinding.ivPhoto.setImageBitmap(decodedImage)
            }

            itemBinding.cvTask.setOnTouchListener (object : OnSwipeTouchListener(context) {
                @SuppressLint("ClickableViewAccessibility")
                override fun onSwipeLeft() {
                    super.onSwipeLeft()
                    itemBinding.lytDelete.visibility = View.VISIBLE
                    itemBinding.lytDelete.setOnClickListener {
                        onClickArrow.onDelete(item)
                    }
                }
                override fun onSwipeRight() {
                    super.onSwipeRight()
                    itemBinding.lytDelete.visibility = View.GONE
                }
                override fun onClick(){
                    onClickArrow.onClickArrow(item)
                    Log.d("ITEM_STATUS", item.status.toString() + item.id_status.toString())
                }

            })


        }
        private fun initArray(){
            val status = STATUS
            val adapter = ArrayAdapter(
                itemBinding.root.context,
                R.layout.list_item,
                status
            )

            with(itemBinding.listStatus) {
                setAdapter(adapter)
                onItemClickListener=this@MainViewHolder
            }
        }

        override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            onClickArrow.onUpdate(taskList[position], p2+1, STATUS[p2])
        }
    }




}