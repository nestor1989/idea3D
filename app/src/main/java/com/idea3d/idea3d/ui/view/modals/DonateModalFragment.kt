package com.idea3d.idea3d.ui.view.modals

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.idea3d.idea3d.databinding.FragmentDonateModalBinding
import com.idea3d.idea3d.utils.Constants

class DonateModalFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentDonateModalBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDonateModalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.floatingActionButton.setOnClickListener { dismiss() }

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constants.DONATE_LINK))
        binding.buttonSeemore.setOnClickListener { startActivity(intent) }

    }

    fun newInstance(): DonateModalFragment {
        val frag = DonateModalFragment()
        val args = Bundle()
        frag.arguments = args
        return frag
    }
}