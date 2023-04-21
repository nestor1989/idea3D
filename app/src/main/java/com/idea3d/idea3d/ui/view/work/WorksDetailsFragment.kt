package com.idea3d.idea3d.ui.view.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentWorksDetailsBinding
import com.idea3d.idea3d.ui.view.adapter.TaskAdapter
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksDetailsFragment : Fragment(), TaskAdapter.OnClickArrow {
    private var _binding: FragmentWorksDetailsBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

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

        initAdapter()
        setUp()

    }

    private fun setUp(){

        if (!arguments?.getString("date").isNullOrEmpty()){
            val date = arguments?.getString("date")
            tasksViewModel.getByDate(date!!).observe(viewLifecycleOwner, Observer { result ->
                callToRepo(result)
            })
        }else if (arguments?.getBoolean("urgent")!=null) {
            val urgent = arguments?.getBoolean("urgent")
            if (urgent!!) tasksViewModel.getUrgent().observe(viewLifecycleOwner, Observer { result->
                callToRepo(result)
            })
        }else
            {
            tasksViewModel.getAllTask().observe(viewLifecycleOwner, Observer { result ->
                callToRepo(result)
            })
        }
    }

    private fun initAdapter(){
        val appContext = requireContext().applicationContext
        binding.rvTasks.layoutManager= LinearLayoutManager(appContext)
    }

    override fun onClickArrow(task: Task) {

    }

    private fun callToRepo(result: Resource<List<Task>>){
        when (result) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                val tasks = result.data
                val adapter = TaskAdapter(tasks, this, requireContext().applicationContext)
                binding.rvTasks.adapter = adapter
            }
            is Resource.Failure -> {
                //binding.prError.visibility=View.VISIBLE
                Toast.makeText(
                    requireContext(),
                    result.exception.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}
