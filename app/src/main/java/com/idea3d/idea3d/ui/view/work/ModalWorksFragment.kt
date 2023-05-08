package com.idea3d.idea3d.ui.view.work

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentModalWorksBinding

class ModalWorksFragment(
    private val task: Task,
    private val onModalWorksClick: OnModalWorksClick
    ): BottomSheetDialogFragment() {

    private var _binding: FragmentModalWorksBinding? = null
    private val binding get() = _binding!!

    interface OnModalWorksClick{
        fun onEdit(task: Task)
        fun onDeleteModal(task: Task)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModalWorksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun newInstance(task: Task): ModalWorksFragment{
        val frag = ModalWorksFragment(task, onModalWorksClick)
        val args = Bundle()
        frag.arguments = args
        return frag
    }

    private fun setUp(){
        binding.floatingActionButton.setOnClickListener { dismiss() }
        task.thing_photo?.let {
            val imageBytes = Base64.decode(task.thing_photo, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            binding.ivPhoto.setImageBitmap(decodedImage)
        }
        binding.tvTitle.text=task.name
        binding.tvDescription.text=task.description
        binding.tvDate.text=task.date_begin
        binding.tvPrice.text = "$${task.price.toString()}"
        binding.tvCost.text = "$${task.cost.toString()}"

        binding.buttonEdit.setOnClickListener {
            onModalWorksClick.onEdit(task)
            dismiss()
        }
        binding.tvDelete.setOnClickListener {
            onModalWorksClick.onDeleteModal(task)
            dismiss()
        }
    }

}