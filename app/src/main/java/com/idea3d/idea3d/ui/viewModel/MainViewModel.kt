package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val homeRepository: HomeRepository,
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    val searchThing = MutableLiveData<String>()
    private val page = MutableLiveData<Int>()
    private val category = MutableLiveData<Int>()


    fun setThings(thingBy:String){
        searchThing.value = thingBy
    }

    fun setPagination(pages:Int){
        page.value = pages
    }

    fun setCategory(cat:Int){
        category.value = cat
    }

    val fetchThings = searchThing.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                if (searchThing.value=="Relevant" || searchThing.value=="popular" || searchThing.value=="newest"){
                emit(homeRepository.getThingsByNews(it, 1, category.value!!))
                }else emit(homeRepository.getThingsByName(it, 1, category.value!!))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchPage = page.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            if (searchThing.value=="Relevant" || searchThing.value=="popular" || searchThing.value=="newest"){
                emit(homeRepository.getThingsByNews(searchThing.value!!, page.value!!, category.value!!))
            }else emit(homeRepository.getThingsByName(searchThing.value!!, page.value!!, category.value!!))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
        }
    }


    fun fetchNewsList(country: String, key: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getNewsUseCase(country, key)
            emit(Resource.Success(result))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


}