package com.idea3d.idea3d.ui.view.work

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentModalWorksBinding

class ModalWorksFragment(private val task: Task) : BottomSheetDialogFragment() {

    private var _binding: FragmentModalWorksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentModalWorksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.floatingActionButton.setOnClickListener { dismiss() }
        val image = "${task.thing_photo+task.thing_extension}"
        Glide.with(this)
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.logoidea)
            .into(binding.ivPhoto)
        binding.tvTitle.text=task.name
        binding.tvDescription.text=task.description
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun newInstance(task: Task): ModalWorksFragment{
        val frag = ModalWorksFragment(task)
        val args = Bundle()
        frag.arguments = args
        return frag
    }

}