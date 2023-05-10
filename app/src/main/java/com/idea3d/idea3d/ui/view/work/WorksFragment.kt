package com.idea3d.idea3d.ui.view.work

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.databinding.FragmentWorksBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.viewModel.TasksViewModel
import com.idea3d.idea3d.utils.Functional
import dagger.hilt.android.AndroidEntryPoint
import ru.cleverpumpkin.calendar.CalendarDate
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.firstDayOfYear
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class WorksFragment : Fragment(), ScheduleDialogFragment.OnDateClick {
    private var _binding: FragmentWorksBinding? = null
    private val binding get() = _binding!!

    private val tasksViewModel by viewModels<TasksViewModel>()

    private lateinit var fav: List<Task>
    private lateinit var scheduleDialogFragment: ScheduleDialogFragment

    private var clientsList = ArrayList<String>()

    private var stringStatus:String?=null

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val firstDayYear = today.with(firstDayOfYear())

    @RequiresApi(Build.VERSION_CODES.O)
    private val firstDayMonth = today.with(firstDayOfMonth())

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
        setFinances()
        getTaskRange()
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
            val bundle = Bundle()
            bundle.putStringArrayList("clients", clientsList)
            Log.d("BUNDLE_ALLLL", bundle.toString())
            findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
        }

        binding.buttonUrgent.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("urgent", true)
            bundle.putStringArrayList("clients", clientsList)
            findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
        }

        setFilters()
    }

    private fun setFilters() {

        binding.btnfilter1.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_1)
            navigateWithStatus(1, stringStatus!!)
        }
        binding.btnfilter2.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_2)
            navigateWithStatus(2, stringStatus!!)
        }
        binding.btnfilter3.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_3)
            navigateWithStatus(3, stringStatus!!)
        }
        binding.btnfilter4.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_4)
            navigateWithStatus(4, stringStatus!!)
        }
        binding.btnfilter5.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_5)
            navigateWithStatus(5, stringStatus!!)
        }
        binding.btnfilter6.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_6)
            navigateWithStatus(6, stringStatus!!)
        }
        binding.btnfilter7.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_7)
            navigateWithStatus(7, stringStatus!!)
        }
        binding.btnfilter8.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_8)
            navigateWithStatus(8, stringStatus!!)
        }
        binding.btnfilter9.setOnClickListener {
            stringStatus = getString(R.string.maker_zone_status_9)
            navigateWithStatus(9, stringStatus!!)
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
        bundle.putStringArrayList("clients", clientsList)
        findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
    }

    private fun navigateWithStatus(idStatus:Int, statusString: String){
        val bundle = Bundle()
        bundle.putInt("idStatus", idStatus)
        bundle.putStringArrayList("clients", clientsList)
        bundle.putString("title", statusString )
        Log.d("BUNDLEEEE", bundle.toString())
        findNavController().navigate(R.id.action_worksFragment_to_worksDetailsFragment, bundle)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTaskRange(){
        Log.d("HOYYYY", today.toString())
        Log.d("AÑOOOO", firstDayYear.toString())

        tasksViewModel.getDateRange(today.toString(), firstDayYear.toString()).observe(viewLifecycleOwner, Observer{ result->
            if (result is Resource.Success){
                val taskInRange = result.data
                Log.d("RANGO_DE_TASK", taskInRange.toString())
            }
        })

    }


}