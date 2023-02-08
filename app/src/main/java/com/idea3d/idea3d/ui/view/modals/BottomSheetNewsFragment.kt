package com.idea3d.idea3d.ui.view.modals

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.databinding.FragmentBottomSheetNewsListDialogBinding

class BottomSheetNewsFragment(private val news: News) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetNewsListDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBottomSheetNewsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.floatingActionButton.setOnClickListener { dismiss() }
        val image = "${news.urlToImage}"
        Glide.with(this)
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.logoidea)
            .into(binding.ivPhoto)
        binding.tvTitle.text=news.title
        binding.tvDescription.text=news.content

        val intent: Intent = Uri.parse("${news.url}").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        binding.buttonSeemore.setOnClickListener { startActivity(intent) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun newInstance(news: News): BottomSheetNewsFragment? {
        val frag = BottomSheetNewsFragment(news)
        val args = Bundle()
        frag.arguments = args
        return frag
    }

}