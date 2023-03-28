package com.idea3d.idea3d.ui.view

import android.content.Intent
import android.net.Uri
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
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.model.ThingWithCat
import com.idea3d.idea3d.databinding.FragmentHomeBinding
import com.idea3d.idea3d.ui.view.adapter.FavsAdapter
import com.idea3d.idea3d.ui.view.adapter.NewsAdapter
import com.idea3d.idea3d.ui.view.adapter.ThingsChildAdapter
import com.idea3d.idea3d.ui.view.adapter.ThingsParentAdapter
import com.idea3d.idea3d.ui.view.modals.BottomSheetNewsFragment
import com.idea3d.idea3d.ui.view.modals.ProgressDialogFragment
import com.idea3d.idea3d.ui.view.modals.ThingsModalFragment
import com.idea3d.idea3d.ui.viewModel.HomeViewModel
import com.idea3d.idea3d.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),
    NewsAdapter.OnNewsClickListener,
    ThingsParentAdapter.OnClickChild,
    ThingsModalFragment.OnThingClickListener,
    FavsAdapter.OnThingClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()

    private var thingsWithCat = mutableListOf<ThingWithCat>()

    private lateinit var bottomSheetNewsFragment: BottomSheetNewsFragment
    private lateinit var thingsModalFragment: ThingsModalFragment
    private lateinit var progressDialogFragment: ProgressDialogFragment


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

        (activity as MainActivity).setThemeHome()

        setUpThingsObservers()
        setUpNewsObservers()
    }

    private fun setUpRecyclerView(thingsWithCat: List<ThingWithCat>) {
        val appContext = requireContext()
        val recyclerView = binding.parentRecyclerView
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        recyclerView.adapter = ThingsParentAdapter(appContext, thingsWithCat, this)
    }

    private fun setUpRecyclerViewFavs(things: List<ThingEntity>) {
        val appContext = requireContext()
        val recyclerView = binding.favsRecycler
        recyclerView.adapter = FavsAdapter(appContext, things, this)
    }

    private fun setUpNewsObservers(){

        mainViewModel.fetchNewsList.observe(viewLifecycleOwner, Observer{ result->
            when(result){
                is Resource.Loading->{
                }
                is Resource.Success->{
                    binding.rvNews.adapter= NewsAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure->{
                    //binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }

        })
    }

    private fun setUpThingsObservers(){
        progressDialogFragment = ProgressDialogFragment()
        val newProgress = progressDialogFragment.newInstance()

        homeViewModel.getFavorites().observe(viewLifecycleOwner, Observer { result ->

            when(result){
                is Resource.Loading->{
                }
                is Resource.Success->{
                    setUpRecyclerViewFavs(result.data)
                }
                is Resource.Failure->{
                    //binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }

        })

        homeViewModel.fetchCategories().observe(viewLifecycleOwner, Observer { result ->

            when(result){
                is Resource.Loading->{
                    newProgress.show(activity?.supportFragmentManager!!, "progress dialog")
                    binding.rvNews.visibility = View.GONE
                }
                is Resource.Success->{
                    println(result.data)
                    for (i in 0 until result.data.size){
                        val nameCat = result.data[i].name
                        val catId = result.data[i].id

                        homeViewModel.fetchThings(catId).observe(viewLifecycleOwner, Observer { things ->
                            when(things){
                                is Resource.Loading->{

                                }
                                is Resource.Success->{
                                    val thingWithCat = ThingWithCat(things.data.thingsList, catId, nameCat)
                                    Log.d("Y ACAAAAAA", thingWithCat.toString())
                                    thingsWithCat.add(thingWithCat)
                                    Log.d("QUE PASAAAA", thingsWithCat.toString())
                                    setUpRecyclerView(thingsWithCat)
                                    binding.rvNews.visibility = View.VISIBLE
                                    newProgress.dismiss()

                                }
                                is Resource.Failure->{
                                    Toast.makeText(requireContext(), things.exception.toString(), Toast.LENGTH_LONG).show()
                                    Log.d("EXCEPCIONN",things.exception.toString() )
                                    binding.rvNews.visibility = View.VISIBLE
                                    progressDialogFragment.dismiss()
                                }

                            }

                        })
                    }

                }
                is Resource.Failure->{
                    newProgress.dismiss()
                    binding.rvNews.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }
        })
    }

    override fun onNewsClick(news: News) {
        bottomSheetNewsFragment = BottomSheetNewsFragment(news)
        val newInst = bottomSheetNewsFragment.newInstance(news)
        newInst?.show(activity?.supportFragmentManager!!, "news")
    }

    override fun onLikeClick(thing: Thing) {
        Toast.makeText(requireContext(), "added to favorites", Toast.LENGTH_LONG).show()
        val thingEntity = ThingEntity(thing.id, thing.name, thing.image, thing.url)
        homeViewModel.addedToFavorite(thingEntity)
    }

    override fun onDownLoadClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onClickChild(thing: Thing) {
        thingsModalFragment = ThingsModalFragment(thing, this)
        val newInst = thingsModalFragment.newInstance(thing)
        newInst.show(activity?.supportFragmentManager!!, "thingmodal")
    }

    override fun onThingClick(thing: Thing) {
        thingsModalFragment = ThingsModalFragment(thing, this)
        val newInst = thingsModalFragment.newInstance(thing)
        newInst.show(activity?.supportFragmentManager!!, "thingmodal")
    }

}