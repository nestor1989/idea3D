package com.idea3d.idea3d.ui.view.work

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
class NewTaskFragment : Fragment(), ScheduleDialogFragment.OnDateClick, AdapterView.OnItemClickListener {

    private var _binding: FragmentNewTaskBinding? = null
    private val binding get() = _binding!!

    private var idStatus = 0
    private var stringStatus = ""

    private val tasksViewModel by viewModels<TasksViewModel>()

    private lateinit var scheduleDialogFragment: ScheduleDialogFragment

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

        binding.dbBegin.setOnClickListener {
            scheduleDialogFragment = ScheduleDialogFragment(this)
            val dateInst = scheduleDialogFragment.newInstance(1)
            dateInst.show(activity?.supportFragmentManager!!,"Dialog Bottom")
        }

        initArray()

        val clients = arguments?.getStringArrayList("clients")
        clients?.let { if (clients.isNotEmpty()) initClientArray(clients) }
    }

    private fun initClientArray(clients: ArrayList<String>) {


        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            clients
        )

        with(binding.etClient) {
            setAdapter(adapter)
        }
    }

    private fun createTask (){
        val nameTask = binding.inputName.text.toString()
        val descriptionTask = binding.inputDescription.text.toString()
        val priority = binding.cbPriority.isChecked
        val price = binding.etPrice.text.toString().toFloat()
        val cost = binding.etCost.text.toString().toFloat()
        val client = binding.etClient.text.toString()

        val task = Task(
            name =nameTask,
            description = descriptionTask,
            prioritize = priority,
            price = price,
            cost = cost,
            date_begin = DISPLAY_DATE,
            id_status = idStatus,
            status = stringStatus,
            client = client
        )

        tasksViewModel.addTask(task)
    }

    companion object{
        var DISPLAY_DATE = "Fecha"
    }

    override fun onDateClick(date:String) {
        binding.dbBegin.setText(DISPLAY_DATE)
    }

    private fun initArray(){
        val status = resources.getStringArray(R.array.status)
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.list_item,
            status
        )

        with(binding.listStatus) {
            setAdapter(adapter)
            onItemClickListener=this@NewTaskFragment

        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            when (p2) {
                0 -> {
                    idStatus = 1
                    stringStatus = getString(R.string.maker_zone_status_1)
                }
                1 -> {
                    idStatus = 2
                    stringStatus = getString(R.string.maker_zone_status_2)
                }
                2 -> {
                    idStatus = 3
                    stringStatus = getString(R.string.maker_zone_status_3)
                }
                3 -> {
                    idStatus = 4
                    stringStatus = getString(R.string.maker_zone_status_4)
                }
                4 -> {
                    idStatus = 5
                    stringStatus = getString(R.string.maker_zone_status_5)
                }
                5 -> {
                    idStatus = 6
                    stringStatus = getString(R.string.maker_zone_status_6)
                }
                6 -> {
                    idStatus = 7
                    stringStatus = getString(R.string.maker_zone_status_7)
                }
                7 -> {
                    idStatus = 8
                    stringStatus = getString(R.string.maker_zone_status_8)
                }
                8 -> {
                    idStatus = 9
                    stringStatus = getString(R.string.maker_zone_status_9)
                }
            }

    }
}