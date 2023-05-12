package com.idea3d.idea3d.ui.view.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.data.model.ListProblems
import com.idea3d.idea3d.databinding.FragmentGuideBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.view.adapter.ProblemsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment(), OnFragmentActionsListener {
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    private lateinit var solutionFragment: SolutionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(inflater, container, false)

        (activity as MainActivity).setThemeMain()

        initAdapter()

        return binding.root
    }

    private fun initAdapter(){
        val appContext = requireContext().applicationContext
        binding.recyclerGuia.layoutManager= LinearLayoutManager(appContext)
        val adapter = ProblemsAdapter(this, ListProblems.errorGuide)
        binding.recyclerGuia.adapter = adapter
    }

    override fun onClickFragmentButton(value:Int, button:Int, image: Int, title:String) {
        val bundle = Bundle()
        bundle.putInt("button", button)
        bundle.putInt("value", value)
        bundle.putInt("image", image)
        bundle.putString("title", title)
        solutionFragment = SolutionFragment()
        val solutionFragmentInst = solutionFragment.newInstance(bundle)
        solutionFragmentInst.show(activity?.supportFragmentManager!!, "solution")

    }

    override fun onImageClick(value: Int, image:Int, title:String) {
        val bundle = Bundle()
        bundle.putInt("value", value)
        bundle.putInt("image", image)
        bundle.putString("title", title)
        solutionFragment = SolutionFragment()
        val solutionFragmentInst = solutionFragment.newInstance(bundle)
        solutionFragmentInst.show(activity?.supportFragmentManager!!, "IMAGE")
    }

}