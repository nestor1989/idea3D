package com.idea3d.idea3d.ui.view.modals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.home.ThingDTO
import com.idea3d.idea3d.data.model.home.ThingEntity
import com.idea3d.idea3d.databinding.FragmentThingsModalBinding

class ThingsModalFragment(
    private val thing: ThingDTO,
    private val onThingClickListener: OnThingClickListener
    ) : BottomSheetDialogFragment() {

    private var _binding: FragmentThingsModalBinding? = null
    private val binding get() = _binding!!

    interface OnThingClickListener  {
        fun onLikeClick(thing: ThingDTO)
        fun onDownLoadClick(url: String)
        fun onDismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThingsModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.floatingActionButton.setOnClickListener {
            onThingClickListener.onDismiss()
            dismiss()
        }

        val image = "${thing.image}"
        Glide.with(requireContext())
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.idea_3d_brand_2020_02)
            .dontAnimate()
            .into(binding.ivPhoto)

        binding.tvTitle.text = thing.name
        binding.ivPhoto.isClickable = true

        binding.buttonFav.isActivated = thing.favorite

        binding.buttonFav.setOnClickListener {
            binding.buttonFav.isActivated = !binding.buttonFav.isActivated
            onThingClickListener.onLikeClick(thing)
        }

        binding.buttonSeemore.setOnClickListener {
            onThingClickListener.onDownLoadClick(thing.url)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        onThingClickListener.onDismiss()
    }

    fun newInstance(thing: ThingDTO): ThingsModalFragment {
        val frag = ThingsModalFragment(thing, onThingClickListener)
        val args = Bundle()
        frag.arguments = args
        return frag
    }



}