package com.idea3d.idea3d.ui.view.home

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.idea3d.idea3d.data.model.home.news.News
import com.idea3d.idea3d.databinding.FragmentBottomSheetNewsListDialogBinding
import com.idea3d.idea3d.ui.view.modals.ProgressDialogFragment

class BottomSheetNewsFragment(private val news: News) : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetNewsListDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var progressDialogFragment: ProgressDialogFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBottomSheetNewsListDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.floatingActionButton.setOnClickListener {
            dismiss()
        }

        try {
            progressDialogFragment = ProgressDialogFragment()
            val newProgress = progressDialogFragment.newInstance()
            newProgress.show(activity?.supportFragmentManager!!, "progress dialog")

            binding.webView.loadUrl(news.url)

            binding.webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    newProgress.dismiss()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.tvTitle.text = news.title
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