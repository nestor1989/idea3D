package com.idea3d.idea3d.ui.view.modals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.idea3d.idea3d.databinding.FragmentProgressDialogBinding

class ProgressDialogFragment : DialogFragment() {

    private var _binding: FragmentProgressDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProgressDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(): ProgressDialogFragment {
        val frag = ProgressDialogFragment()
        return frag
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
