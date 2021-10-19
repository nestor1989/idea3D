package com.idea3d.idea3d.ui.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.idea3d.idea3d.data.model.Problemas
import com.idea3d.idea3d.data.model.problemasModel.Companion.guiaErrores
import com.idea3d.idea3d.databinding.FragmentSolucionBinding
import com.idea3d.idea3d.ui.view.OnFragmentActionsListener

class SolucionFragment  : Fragment() {

    private var _binding: FragmentSolucionBinding? = null
    private val binding get() = _binding!!

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

        _binding= FragmentSolucionBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val boton = requireArguments().getInt("boton")
            val valor = requireArguments().getInt("valor")
            val imagen= requireArguments().getInt("imagen")

            when (boton){
                0->{ binding.imageView.visibility = View.VISIBLE
                    binding.imageView.setImageResource(imagen)
                }
                1, 2, 3 -> {binding.textView.text = "el valor es$valor y el boton es $boton"}
            }



        }
    }





    override fun onDetach() {
        super.onDetach()
        listener = null
    }



}

