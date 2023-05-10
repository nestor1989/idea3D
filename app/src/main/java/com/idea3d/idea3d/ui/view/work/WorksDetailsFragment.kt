package com.idea3d.idea3d.ui.view.work

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentWorksDetailsBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.view.adapter.TaskAdapter
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import com.idea3d.idea3d.utils.Functional
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksDetailsFragment : Fragment(), TaskAdapter.OnClickArrow, ModalWorksFragment.OnModalWorksClick {
    private var _binding: FragmentWorksDetailsBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

    private lateinit var modalWorksFragment: ModalWorksFragment

    private lateinit var adapter: TaskAdapter

    private lateinit var tasks: MutableList<Task>

    var clients: ArrayList<String>? = arrayListOf()

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

        val status = resources.getStringArray(R.array.status)
        STATUS = status

        Log.d("LISTA_DE_STATUS", STATUS.toString())

        initAdapter()
        setUp()

    }

    private fun setUp(){

        var title = arguments?.getString("title")

        if (title.isNullOrEmpty()) title = "Tus trabajos"

        (activity as MainActivity).setNoBanner(title)

        val date = arguments?.getString("date")
        val urgent = arguments?.getBoolean("urgent")
        val idStatus = arguments?.getInt("idStatus")
        clients = arguments?.getStringArrayList("clients")

        if (!date.isNullOrEmpty()) {
            tasksViewModel.getByDate(Functional.convertDatesToSQL(date)).observe(viewLifecycleOwner, Observer { result ->
                callToRepo(result)
            })
        } else if (urgent != null && urgent) {
            tasksViewModel.getUrgent().observe(viewLifecycleOwner, Observer { result ->
                callToRepo(result)
            })
        } else if (idStatus !=null && idStatus!=0) {
            Log.d("ID_STATUSSS", idStatus.toString())
            tasksViewModel.getByStatus(idStatus).observe(viewLifecycleOwner, Observer { result ->
                callToRepo(result)
            })
        } else {
            tasksViewModel.getAllTask().observe(viewLifecycleOwner, Observer { result ->
                callToRepo(result)
            })
        }

        binding.floatingActionButton2.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("clients", clients)
            findNavController().navigate(R.id.action_worksDetailsFragment_to_newTaskFragment, bundle)
        }
    }

    private fun initAdapter(){
        val appContext = requireContext().applicationContext
        binding.rvTasks.layoutManager= LinearLayoutManager(appContext)
    }

    private fun setAdapter(tasks: List<Task>){
        adapter = TaskAdapter(tasks, this, requireContext().applicationContext)
        binding.rvTasks.adapter = adapter
    }

    override fun onClickArrow(task: Task) {
        modalWorksFragment = ModalWorksFragment(task, this)
        val modalInst = modalWorksFragment.newInstance(task)
        modalInst.show(activity?.supportFragmentManager!!, "taskmodal")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDelete(task: Task) {
        tasksViewModel.deleteTask(task)
        tasks.remove(task)
        adapter.notifyDataSetChanged()
    }

    override fun onUpdate(task: Task, idStatus: Int, stringStatus: String) {
        task.id_status = idStatus
        task.status = stringStatus
        tasksViewModel.updateTask(task)
    }

    private fun callToRepo(result: Resource<List<Task>>){
        when (result) {
            is Resource.Loading -> {}
            is Resource.Success -> {
                tasks = (result.data).toMutableList()
                Log.d("RESULTADOSS", result.data.toString())
                setAdapter(tasks)
                setUpSearchView()

                if (tasks.isEmpty()) {
                    binding.notTasks.visibility = View.VISIBLE
                    binding.search.visibility = View.GONE
                }
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

    private fun setUpSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                var search = p0!!
                showFilterTask(search)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var search = p0!!
                showFilterTask(search)
                return false
            }
        })
    }

    private fun showFilterTask(search:String){
        var tasksFilter: ArrayList<Task> = arrayListOf()
        for (i in tasks.indices){
            if (tasks[i].name.contains(search, ignoreCase = true) || tasks[i].client!!.contains(search, ignoreCase = true)){
                tasksFilter.add(tasks[i])
            }
        }
        setAdapter(tasksFilter)
    }

    companion object{
        var STATUS: Array<String> = arrayOf<String>()
    }

    override fun onEdit(task: Task) {
        val bundle = Bundle()
        bundle.putStringArrayList("clients", clients)
        bundle.putParcelable("task", task)
        findNavController().navigate(R.id.action_worksDetailsFragment_to_newTaskFragment, bundle)
    }

    override fun onDeleteModal(task: Task) {
        onDelete(task)
    }
}
