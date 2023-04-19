package com.idea3d.idea3d.ui.view.work

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentHomeBinding
import com.idea3d.idea3d.databinding.FragmentWorksBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.view.adapter.NewsAdapter
import com.idea3d.idea3d.ui.viewModel.HomeViewModel
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.cos

@AndroidEntryPoint
class WorksFragment : Fragment() {
    private var _binding: FragmentWorksBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

    private lateinit var fav: List<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        setFinances()

    }

    private fun setUp(){
        (activity as MainActivity).setThemeHome()

        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_worksFragment_to_newTaskFragment)
        }

        binding.btnfilter1.setOnClickListener {
            navigation()
        }
    }

    private fun navigation(){
        findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment)
    }

    @SuppressLint("SetTextI18n")
    private fun setFinances(){
        tasksViewModel.getFavorites().observe(viewLifecycleOwner, Observer{ result->
            when(result){
                is Resource.Loading->{}
                is Resource.Success->{
                    var profits = 0.0
                    var cost = 0.0
                    var sales = 0.0

                    fav = result.data
                    println(fav)

                    for (i in fav.indices){
                        sales += fav[i].price!!
                        cost += fav[i].cost!!
                    }

                    profits = sales - cost

                    binding.tvSales.text = "$${sales}"
                    binding.tvCost.text = "$${cost}"
                    binding.tvProfits.text = "$${profits}"
                }
                is Resource.Failure->{
                    //binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }

        })
    }


}