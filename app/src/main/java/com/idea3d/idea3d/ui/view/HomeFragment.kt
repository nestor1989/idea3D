package com.idea3d.idea3d.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.core.Constants
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.ThingWithCat
import com.idea3d.idea3d.databinding.FragmentHomeBinding
import com.idea3d.idea3d.ui.view.adapter.MainAdapter
import com.idea3d.idea3d.ui.view.adapter.ThingsParentAdapter
import com.idea3d.idea3d.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private var thingsWithCat = mutableListOf<ThingWithCat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setUp()

        return binding.root
    }

    private fun setUp(){

        (activity as MainActivity).setThemeMain()

        viewModel.fetchCategories().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{

                }
                is Resource.Success->{
                    println(result.data)
                    for (i in 0 until result.data.size){
                        val nameCat = result.data[i].name
                        val catId = result.data[i].id

                        viewModel.fetchThings(catId).observe(viewLifecycleOwner, Observer { things ->
                            when(things){
                                is Resource.Loading->{

                                }
                                is Resource.Success->{
                                    val thingWithCat = ThingWithCat(things.data.thingsList, catId, nameCat)
                                    Log.d("Y ACAAAAAA", thingWithCat.toString())
                                    thingsWithCat.add(thingWithCat)

                                    Log.d("QUE PASAAAA", thingsWithCat.toString())
                                    setUpRecyclerView(thingsWithCat)
                                }
                                is Resource.Failure->{
                                    Toast.makeText(requireContext(), things.exception.toString(), Toast.LENGTH_LONG).show()
                                    Log.d("EXCEPCIONN",things.exception.toString() )
                                }

                            }

                        })
                    }

                }
                is Resource.Failure->{
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }
        })

    }

    private fun setUpRecyclerView(thingsWithCat: List<ThingWithCat>) {
        val appContext = requireContext()
        val recyclerView = binding.parentRecyclerView
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        recyclerView.adapter = ThingsParentAdapter(appContext, thingsWithCat)
    }

}