package com.idea3d.idea3d


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.idea3d.idea3d.databinding.FragmentSolucionBinding

class SolucionFragment : Fragment() {

    private var _binding:FragmentSolucionBinding?=null
    private val binding get()=_binding!!

    private var listener: OnFragmentActionsListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentSolucionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPlus.setOnClickListener { listener?.onClickFragmentButton() }
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }



}

