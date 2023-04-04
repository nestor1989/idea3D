package com.idea3d.idea3d.ui.view.search

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.Constants
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.databinding.FragmentMainBinding
import com.idea3d.idea3d.ui.view.MainActivity
import com.idea3d.idea3d.ui.view.adapter.MainAdapter
import com.idea3d.idea3d.ui.view.adapter.NewsAdapter
import com.idea3d.idea3d.ui.view.adapter.PaginationAdapter
import com.idea3d.idea3d.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment :
    Fragment(),
    MainAdapter.OnThingClickListener,
    NewsAdapter.OnNewsClickListener,
    PaginationAdapter.OnPageClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()

    lateinit var listPages:MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        (activity as MainActivity).setThemeMain()
        (activity as MainActivity).setCurrentNavController(0)

        arguments?.let {
            viewModel.setCategory(it.getInt("category"))
            binding.tvCat.setText(it.getString("category_string"))
            binding.tvCat.visibility = View.VISIBLE
        }

        setUpRecyclerView()
        setUpObservers()
        setUpSearchView()
        setUpButtons()

        return binding.root
    }

    private fun setUpObservers(){
        viewModel.fetchThings.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prNoThing.visibility = View.GONE
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.rvThings.visibility = View.VISIBLE
                    binding.prNoThing.visibility = View.GONE
                    binding.searchLayout.visibility = View.VISIBLE
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvThings.adapter= MainAdapter(requireContext(), result.data.thingsList, this)
                    var page = result.data.totalThings / Constants.PER_PAGE
                    if (page==0) page++
                    setUpPaginationRecycler(page)
                    binding.rvPage.visibility = View.VISIBLE
                }
                is Resource.Failure->{
                    binding.rvThings.visibility=View.GONE
                    binding.prBar.visibility=View.GONE

                    val cm = (activity as MainActivity).getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

                    if (!isConnected){
                        binding.prError.visibility=View.VISIBLE
                    }else binding.prNoThing.visibility = View.VISIBLE

                    binding.rvPage.visibility = View.GONE

                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }
        })

        viewModel.fetchPage.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading->{
                    binding.prBar.visibility=View.VISIBLE
                    binding.prError.visibility=View.GONE
                }
                is Resource.Success->{
                    binding.prBar.visibility=View.GONE
                    binding.prError.visibility=View.GONE
                    binding.rvThings.adapter= MainAdapter(requireContext(), result.data.thingsList, this)
                    /*var page = result.data.totalThings / Constants.PER_PAGE
                    if (page==0) page++
                    setUpPaginationRecycler(page)*/
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

    private fun setUpPaginationRecycler(total:Int){
        listPages = mutableListOf<Int>()
        for (i in 1..total) {
            listPages.add(i-1,i)
        }
        binding.rvPage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPage.adapter = PaginationAdapter(requireContext(), listPages , this )
    }

    override fun onThingClick(thing: Thing) {
        val intent: Intent = Uri.parse("${thing.url}").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(intent)
    }

    override fun onNewsClick(news: News) {
        binding.dialogNews.visibility = View.VISIBLE
        val image = "${news.urlToImage}"
        Glide.with(this)
            .load(image)
            .centerCrop()
            .placeholder(R.drawable.logoidea)
            .into(binding.includeNews.ivPortada)
        binding.includeNews.tvTitle.text=news.title
        binding.includeNews.tvDesc.text=news.content
        binding.includeNews.floatingActionButton.setOnClickListener { binding.dialogNews.visibility = View.GONE}
        val intent: Intent = Uri.parse("${news.url}").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)}
        binding.includeNews.button.setOnClickListener { startActivity(intent) }
    }

    private fun setUpSearchView() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                var search = p0!!
                viewModel.setThings(search)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var search = p0!!
                viewModel.setThings(search)
                return false
            }
        })
    }

    private fun setUpButtons(){
        binding.newButton.setOnClickListener {
            viewModel.setThings("newest")
        }

        binding.popButton.setOnClickListener {
            viewModel.setThings("popular")
        }

    }

    override fun onPageClick(page: Int) {
        viewModel.setPagination(page)
    }

}