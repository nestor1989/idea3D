package com.idea3d.idea3d

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
import com.idea3d.idea3d.data.DataSource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.repo.RepoImpl
import com.idea3d.idea3d.databinding.FragmentGuideBinding
import com.idea3d.idea3d.databinding.FragmentMainBinding
import com.idea3d.idea3d.ui.view.adapter.MainAdapter
import com.idea3d.idea3d.ui.view.adapter.NewsAdapter
import com.idea3d.idea3d.ui.viewModel.MainViewModel
import com.idea3d.idea3d.ui.viewModel.VMFactory

class MainFragment : Fragment(), MainAdapter.OnThingClickListener, NewsAdapter.OnNewsClickListener {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>(){ VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        setUpNewsRecyclerView()
        setUpObservers()

        return binding.root
    }

    private fun setUpObservers(){
        viewModel.fetchThings.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvThings.adapter= MainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }
        })

        viewModel.fetchNewsList.observe(viewLifecycleOwner, Observer{ result->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvNews.adapter= NewsAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }

        })
    }

    private fun setUpRecyclerView() {
        val appContext = requireContext().applicationContext
        val recyclerView = binding.rvThings
        recyclerView.layoutManager= LinearLayoutManager(appContext)
    }

    private fun setUpNewsRecyclerView() {
        val appContext = requireContext().applicationContext
        val recyclerView = binding.rvNews
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        binding.rvNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onThingClick(thing: Thing) {

    }

    override fun onNewsClick(news: News) {
        TODO("Not yet implemented")
    }

}