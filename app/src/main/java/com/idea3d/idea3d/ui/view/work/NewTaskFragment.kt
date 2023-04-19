package com.idea3d.idea3d.ui.view.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.idea3d.idea3d.R
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentNewTaskBinding
import com.idea3d.idea3d.databinding.FragmentWorksDetailsBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()

    }

    private fun setUp(){
        (activity as MainActivity).setThemeHome()

        binding.buttonSend.setOnClickListener {
            createTask()
            findNavController().navigate(R.id.action_newTaskFragment_to_worksDetailsFragment)
        }
    }

    private fun createTask (){
        val nameTask = binding.inputName.text.toString()
        val descriptionTask = binding.inputDescription.text.toString()
        val priority = binding.cbPriority.isChecked
        val price = binding.etPrice.text.toString().toFloat()
        val cost = binding.etCost.text.toString().toFloat()

        val task = Task(
            name =nameTask,
            description = descriptionTask,
            prioritize = priority,
            price = price,
            cost = cost
        )

        tasksViewModel.addTask(task)
    }
}