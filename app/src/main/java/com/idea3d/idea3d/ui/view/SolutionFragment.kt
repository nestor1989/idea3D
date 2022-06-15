package com.idea3d.idea3d.ui.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.idea3d.idea3d.R
import com.idea3d.idea3d.databinding.FragmentSolucionBinding

class SolutionFragment  : Fragment() {

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

        binding.imageButton.setOnClickListener { requireActivity().onBackPressed() }

        if (arguments != null) {
            val boton = requireArguments().getInt("boton")
            val valor = requireArguments().getInt("valor")
            val imagen = requireArguments().getInt("imagen")

            when (boton) {
                0 -> {
                    binding.imageView.visibility = View.VISIBLE
                    binding.imageView.setImageResource(imagen)
                    binding.fondo.background = null
                }

                1-> {
                    when (valor) {
                        0 -> {
                            binding.textView.text = getString(R.string.b1v0)
                        }
                        1 -> {binding.textView.text = getString(R.string.b1v1)
                        }
                        2 -> {binding.textView.text = getString(R.string.b1v2)
                        }
                        3 -> {binding.textView.text = getString(R.string.b1v3)
                        }
                        4 -> {binding.textView.text = getString(R.string.b1v4)
                        }
                        5 -> {binding.textView.text = getString(R.string.b1v5)
                        }
                        6 -> {binding.textView.text = getString(R.string.b1v6)
                        }
                        7 -> {binding.textView.text = getString(R.string.b1v7)
                        }
                        8 -> {binding.textView.text = getString(R.string.b1v8)
                        }
                        9 -> {binding.textView.text = getString(R.string.b1v9)
                        }
                        10 -> {binding.textView.text = getString(R.string.b1v10)
                        }
                        11 -> {binding.textView.text = getString(R.string.b1v11)
                        }
                        12 -> {binding.textView.text = getString(R.string.b1v12)
                        }
                        13 -> {binding.textView.text = getString(R.string.b1v13)
                        }
                        14 -> {binding.textView.text = getString(R.string.b1v14)
                        }
                        15 -> {binding.textView.text = getString(R.string.b1v15)
                        }
                        16 -> {binding.textView.text = getString(R.string.b1v16)
                        }
                        17 -> {binding.textView.text = getString(R.string.b1v17)
                        }
                        18 -> {binding.textView.text = getString(R.string.b1v18)
                        }
                        19 -> {binding.textView.text = getString(R.string.b1v19)
                        }
                        20 -> {binding.textView.text = getString(R.string.b1v20)
                        }
                        21 -> {binding.textView.text = getString(R.string.b1v21)
                        }
                        22 -> {binding.textView.text = getString(R.string.b1v22)
                        }
                    }
                }

                2-> {
                    when (valor) {
                        0 -> {
                            binding.textView.text = getString(R.string.b2v0)
                        }
                        1 -> {binding.textView.text = getString(R.string.b2v1)
                        }
                        2 -> {binding.textView.text = getString(R.string.b2v2)
                        }
                        4 -> {binding.textView.text = getString(R.string.b2v4)
                        }
                        5 -> {binding.textView.text = getString(R.string.b2v5)
                        }
                        6 -> {binding.textView.text = getString(R.string.b2v6)
                        }
                        7 -> {binding.textView.text = getString(R.string.b2v7)
                        }
                        8 -> {binding.textView.text = getString(R.string.b2v8)
                        }
                        9 -> {binding.textView.text = getString(R.string.b2v9)
                        }
                        10 -> {binding.textView.text = getString(R.string.b2v10)
                        }
                        11 -> {binding.textView.text = getString(R.string.b2v11)
                        }
                        12 -> {binding.textView.text = getString(R.string.b2v12)
                        }
                        13 -> {binding.textView.text = getString(R.string.b2v13)
                        }
                        14 -> {binding.textView.text = getString(R.string.b2v14)
                        }
                        15 -> {binding.textView.text = getString(R.string.b2v15)
                        }
                        17 -> {binding.textView.text = getString(R.string.b2v17)
                        }
                        18 -> {binding.textView.text = getString(R.string.b2v18)
                        }
                        19 -> {binding.textView.text = getString(R.string.b2v19)
                        }
                        20 -> {binding.textView.text = getString(R.string.b2v20)
                        }
                        22 -> {binding.textView.text = getString(R.string.b2v22)
                        }
                    }
                }

                3-> {
                    when (valor) {
                        0 -> {
                            binding.textView.text = getString(R.string.b3v0)
                        }
                        1 -> {binding.textView.text = getString(R.string.b3v1)
                        }
                        4 -> {binding.textView.text = getString(R.string.b3v4)
                        }
                        5 -> {binding.textView.text = getString(R.string.b3v5)
                        }
                        6 -> {binding.textView.text = getString(R.string.b3v6)
                        }
                        7 -> {binding.textView.text = getString(R.string.b3v7)
                        }
                        8 -> {binding.textView.text = getString(R.string.b3v8)
                        }
                        9 -> {binding.textView.text = getString(R.string.b3v9)
                        }
                        10 -> {binding.textView.text = getString(R.string.b3v10)
                        }
                        11 -> {binding.textView.text = getString(R.string.b3v11)
                        }

                        13 -> {binding.textView.text = getString(R.string.b3v13)
                        }
                        14 -> {binding.textView.text = getString(R.string.b3v14)
                        }

                        17 -> {binding.textView.text = getString(R.string.b3v17)
                        }
                        19 -> {binding.textView.text = getString(R.string.b3v19)
                        }
                        22 -> {binding.textView.text = getString(R.string.b3v22)
                        }
                    }
                }
            }

        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }



}

