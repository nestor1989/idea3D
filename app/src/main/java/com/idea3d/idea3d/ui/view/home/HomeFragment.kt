package com.idea3d.idea3d.ui.view.home

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idea3d.idea3d.R
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.home.ThingDTO
import com.idea3d.idea3d.data.model.home.ThingWithCatDTO
import com.idea3d.idea3d.data.model.home.news.NewsDTO
import com.idea3d.idea3d.databinding.FragmentHomeBinding
import com.idea3d.idea3d.ui.view.main.MainActivity
import com.idea3d.idea3d.ui.view.adapter.AlternativeNewsAdapter
import com.idea3d.idea3d.ui.view.adapter.FavsAdapter
import com.idea3d.idea3d.ui.view.adapter.NewsAdapter
import com.idea3d.idea3d.ui.view.adapter.ThingsParentAdapter
import com.idea3d.idea3d.ui.view.modals.ProgressDialogFragment
import com.idea3d.idea3d.ui.view.modals.ThingsModalFragment
import com.idea3d.idea3d.ui.viewModel.HomeViewModel
import com.idea3d.idea3d.ui.viewModel.MainViewModel
import com.idea3d.idea3d.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(),
    NewsAdapter.OnNewsClickListener,
    ThingsParentAdapter.OnClickChild,
    ThingsModalFragment.OnThingClickListener,
    AlternativeNewsAdapter.OnThingClickListener,
    FavsAdapter.OnThingClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()
    private val mainViewModel by viewModels<MainViewModel>()

    private var thingsWithCat = mutableListOf<ThingWithCatDTO>()

    private lateinit var bottomSheetNewsFragment: BottomSheetNewsFragment
    private lateinit var thingsModalFragment: ThingsModalFragment
    private lateinit var progressDialogFragment: ProgressDialogFragment

    var listFavs: List<ThingDTO>?=null


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

    private fun setUpRecyclerView(thingsWithCat: List<ThingWithCatDTO>) {
        val appContext = requireContext()
        val recyclerView = binding.parentRecyclerView
        recyclerView.layoutManager= LinearLayoutManager(appContext)
        recyclerView.adapter = ThingsParentAdapter(appContext, thingsWithCat, this)
    }

    private fun setUpRecyclerViewFavs(things: List<ThingDTO>) {
        val appContext = requireContext()
        val recyclerView = binding.favsRecycler
        recyclerView.adapter = FavsAdapter(appContext, things, this)
    }

    private fun setUpRecyclerAlternative(things: List<ThingDTO>) {
        val appContext = requireContext()
        val recyclerView = binding.rvNews
        recyclerView.adapter = AlternativeNewsAdapter(appContext, things, this)
    }

    private fun setUpNewsObservers(){
        homeViewModel.fetchNewsList(getString(R.string.country), "3D AND " + getString(R.string.printer)).observe(viewLifecycleOwner, Observer{ result->
            when(result){
                is Resource.Loading->{}
                is Resource.Success->{
                    binding.rvNews.adapter= NewsAdapter(requireContext(), result.data, this)
                    Log.d("NEWS", result.data.toString())
                }
                is Resource.Failure->{
                    Log.d("HomeFragment_NEWS", "Error: ${result.exception}")
                    mainViewModel.setThings(Constants.NEWEST)
                    mainViewModel.fetchPage.observe(viewLifecycleOwner, Observer { result ->
                    if (result is Resource.Success){
                        setUpRecyclerAlternative(result.data.thingsList)
                        }
                    })
                }

            }

        })
    }

    private fun setUpThingsObservers(){
        progressDialogFragment = ProgressDialogFragment()
        val newProgress = progressDialogFragment.newInstance()

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
                                is Resource.Loading->{}
                                is Resource.Success->{
                                    val thingWithCat = ThingWithCatDTO(things.data.thingsList, catId, nameCat)

                                    thingsWithCat.add(thingWithCat)
                                    setUpRecyclerView(thingsWithCat)
                                    binding.rvNews.visibility = View.VISIBLE
                                    setUpFavs()
                                    newProgress.dismiss()

                                }
                                is Resource.Failure->{
                                    Toast.makeText(requireContext(), things.exception.toString(), Toast.LENGTH_LONG).show()
                                    Log.d("EXCEPCIONN",things.exception.toString() )
                                    binding.rvNews.visibility = View.VISIBLE
                                    newProgress.dismiss()
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

    override fun onNewsClick(news: NewsDTO) {
        bottomSheetNewsFragment = BottomSheetNewsFragment(news)
        val newInst = bottomSheetNewsFragment.newInstance(news)
        newInst?.show(activity?.supportFragmentManager!!, "news")
    }

    override fun onLikeClick(thing: ThingDTO) {
        if (!thing.favorite){
            homeViewModel.addedToFavorite(thing)
        }
        else {
            homeViewModel.deleteFavorite(thing)
        }
    }

    override fun onDownLoadClick(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onDismiss() {
        setUpFavs()
    }

    //NORMALES
    override fun onClickChild(thing: ThingDTO) {
        modalInst(thing)
    }

    override fun onSearchByCat(category: Int, categoryName: String) {
        val bundle = Bundle()
        bundle.putInt("category", category)
        bundle.putString("category_string", categoryName)
        findNavController().navigate(R.id.action_homeFragment_to_mainFragment, bundle)
    }

    //FAVORITOS
    override fun onThingClick(thing: ThingDTO) {
        modalInst(thing)
    }

    private fun modalInst(thing: ThingDTO){
        val favorite = validateFav(thing)
        thing.favorite = favorite
        thingsModalFragment = ThingsModalFragment(thing, this)
        val newInst = thingsModalFragment.newInstance(thing)
        newInst.show(activity?.supportFragmentManager!!, "thingmodal")
    }

    private fun setUpFavs(){
        homeViewModel.getFavorites().observe(viewLifecycleOwner, Observer { result ->

            when(result){
                is Resource.Loading->{
                }
                is Resource.Success->{
                    listFavs = result.data
                    if (result.data.isNotEmpty()) binding.tvFavs.visibility = View.VISIBLE
                    else binding.tvFavs.visibility = View.GONE
                    setUpRecyclerViewFavs(listFavs!!)
                }
                is Resource.Failure->{
                    //binding.prError.visibility=View.VISIBLE
                    Toast.makeText(requireContext(), result.exception.toString(), Toast.LENGTH_LONG).show()
                }

            }

        })
    }

    private fun validateFav(thing : ThingDTO): Boolean{
        listFavs?.let {
            for(i in 0 until listFavs!!.size){
                if (listFavs!![i].id == thing.id){
                    thing.favorite = true
                }
            }
        }
        return thing.favorite
    }

    override fun onNewThingClick(thing: ThingDTO) {
        modalInst(thing)
    }

}