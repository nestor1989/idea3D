package com.idea3d.idea3d.ui.view.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.idea3d.idea3d.R
import com.idea3d.idea3d.databinding.FragmentWorksBinding
import com.idea3d.idea3d.databinding.FragmentWorksDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksDetailsFragment : Fragment() {
    private var _binding: FragmentWorksDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorksDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}