package com.idea3d.idea3d.ui.view.guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.data.model.ListProblems
import com.idea3d.idea3d.data.model.Problems
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentGuideBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.view.adapter.ProblemsAdapter
import com.idea3d.idea3d.ui.view.adapter.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : Fragment(), OnFragmentActionsListener {
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!

    private lateinit var solutionFragment: SolutionFragment

    private lateinit var adapter: ProblemsAdapter

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
        setUpSearchView()

        return binding.root
    }

    private fun initAdapter(){
        val appContext = requireContext().applicationContext
        binding.recyclerGuia.layoutManager= LinearLayoutManager(appContext)
        adapter = ProblemsAdapter(this, ListProblems.errorGuide)
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

    private fun setUpSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                var search = p0!!
                showFilterList(search)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var search = p0!!
                showFilterList(search)
                return false
            }
        })
    }

    private fun showFilterList(search:String){
        var listFilter: ArrayList<Problems> = arrayListOf()
        val list = ListProblems.errorGuide
        for (i in list.indices){
            if (list[i].name.contains(search, ignoreCase = true) || list[i].description.contains(search, ignoreCase = true)){
                listFilter.add(list[i])
            }
        }
        setAdapter(listFilter)
    }

    private fun setAdapter(list: ArrayList<Problems>){
        adapter = ProblemsAdapter(this, list)
        binding.recyclerGuia.adapter = adapter
    }

}