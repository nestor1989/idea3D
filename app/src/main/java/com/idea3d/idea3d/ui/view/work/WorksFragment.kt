package com.idea3d.idea3d.ui.view.work

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

    }

    private fun setUp(){
        (activity as MainActivity).setThemeHome()

        binding.btnQuick1.setOnClickListener {
            findNavController().navigate(R.id.action_worksFragment_to_newTaskFragment)
        }

        binding.btnQuick2.setOnClickListener {

        }

        binding.btnQuick4.setOnClickListener {
            tasksViewModel.getFavorites().observe(viewLifecycleOwner, Observer{ result->
                when(result){
                    is Resource.Loading->{}
                    is Resource.Success->{
                        fav = result.data
                        println(fav)
                    }
                    is Resource.Failure->{
                        //binding.prError.visibility=View.VISIBLE
                        Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                    }

                }

            })

        }

        binding.btnQuick3.setOnClickListener {
            tasksViewModel.deleteTask(fav[1])
        }
    }


}