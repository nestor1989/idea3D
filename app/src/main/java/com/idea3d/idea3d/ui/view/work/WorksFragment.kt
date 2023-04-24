package com.idea3d.idea3d.ui.view.work

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
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
import com.idea3d.idea3d.databinding.FragmentWorksBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WorksFragment : Fragment(), ScheduleDialogFragment.OnDateClick {
    private var _binding: FragmentWorksBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

    private lateinit var fav: List<Task>
    private lateinit var scheduleDialogFragment: ScheduleDialogFragment

    private var clientsList = ArrayList<String>()

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
            val bundle = Bundle()
            bundle.putStringArrayList("clients", clientsList)
            findNavController().navigate(R.id.action_worksFragment_to_newTaskFragment, bundle)
        }

        binding.buttonCalendar.setOnClickListener {
            scheduleDialogFragment = ScheduleDialogFragment(this)
            val dateInst = scheduleDialogFragment.newInstance(0)
            dateInst.show(activity?.supportFragmentManager!!,"Dialog Bottom")
        }

        binding.buttonAll.setOnClickListener {
            findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment)
        }

        binding.buttonUrgent.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("urgent", true)
            findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
        }

        setFilters()
    }

    private fun setFilters() {

        binding.btnfilter1.setOnClickListener {
            navigateWithStatus(1)
        }
        binding.btnfilter2.setOnClickListener {
            navigateWithStatus(2)
        }
        binding.btnfilter3.setOnClickListener {
            navigateWithStatus(3)
        }
        binding.btnfilter4.setOnClickListener {
            navigateWithStatus(4)
        }
        binding.btnfilter5.setOnClickListener {
            navigateWithStatus(5)
        }
        binding.btnfilter6.setOnClickListener {
            navigateWithStatus(6)
        }
        binding.btnfilter7.setOnClickListener {
            navigateWithStatus(7)
        }
        binding.btnfilter8.setOnClickListener {
            navigateWithStatus(8)
        }
        binding.btnfilter9.setOnClickListener {
            navigateWithStatus(9)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setFinances(){
        tasksViewModel.getAllTask().observe(viewLifecycleOwner, Observer{ result->
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

                        fav[i].client?.let {
                            var clientFlag = false
                            for (j in clientsList.indices){
                                if (it == clientsList[j]) {
                                    clientFlag = true
                                    break
                                }
                            }
                            if (!clientFlag) clientsList.add(it)
                        }
                    }

                    Log.d("LISTA_DE_CLIENTES", clientsList.toString())

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

    override fun onDateClick(date:String) {
        val bundle = Bundle()
        bundle.putString("date", date)
        findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
    }

    private fun navigateWithStatus(idStatus:Int){
        val bundle = Bundle()
        bundle.putInt("idStatus", idStatus)
        Log.d("BUNDLEEEE", bundle.toString())
        findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
    }


}