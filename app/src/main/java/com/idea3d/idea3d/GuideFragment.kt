package com.idea3d.idea3d

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.data.model.ListProblems
import com.idea3d.idea3d.databinding.FragmentGuideBinding
import com.idea3d.idea3d.databinding.FragmentMainBinding
import com.idea3d.idea3d.ui.view.OnFragmentActionsListener
import com.idea3d.idea3d.ui.view.SolutionFragment
import com.idea3d.idea3d.ui.view.adapter.ProblemsAdapter

class GuideFragment : Fragment(), OnFragmentActionsListener {
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(inflater, container, false)

        initAdapter()

        return binding.root
    }

    fun initAdapter(){

        binding.recyclerGuia.layoutManager= LinearLayoutManager(requireContext())
        val adapter = ProblemsAdapter(this, ListProblems.errorGuide)
        binding.recyclerGuia.adapter = adapter
        binding.recyclerGuia.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    }

    override fun onClickFragmentButton(valor:Int, boton:Int) {

        val fragment= SolutionFragment()
        val bundle = Bundle()
        bundle.putInt("valor", valor)
        bundle.putInt("boton", boton)
        fragment.arguments=bundle
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onImageClick(valor: Int, imagen:Int) {
        val fragment= SolutionFragment()
        val bundle = Bundle()
        bundle.putInt("valor", valor)
        bundle.putInt("imagen", imagen)
        fragment.arguments=bundle
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}