package com.idea3d.idea3d.ui.view.work

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idea3d.idea3d.data.model.works.TaskDTO
import com.idea3d.idea3d.databinding.FragmentModalWorksBinding
import com.idea3d.idea3d.utils.Functional

class ModalWorksFragment(
    private val task: TaskDTO,
    private val onModalWorksClick: OnModalWorksClick
    ): BottomSheetDialogFragment() {

    private var _binding: FragmentModalWorksBinding? = null
    private val binding get() = _binding!!

    interface OnModalWorksClick{
        fun onEdit(task: TaskDTO)
        fun onDeleteModal(task: TaskDTO)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModalWorksBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun newInstance(task: TaskDTO): ModalWorksFragment{
        val frag = ModalWorksFragment(task, onModalWorksClick)
        val args = Bundle()
        frag.arguments = args
        return frag
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUp(){
        binding.floatingActionButton.setOnClickListener { dismiss() }
        task.thing_photo?.let {
            val imageBytes = Base64.decode(task.thing_photo, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            binding.ivPhoto.setImageBitmap(decodedImage)
        }
        binding.tvTitle.text=task.name
        binding.tvClient.text = task.client
        binding.tvDescription.text=task.description
        binding.tvDate.text= task.date_begin?.let { Functional.convertDatesToDisplay(it) }
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