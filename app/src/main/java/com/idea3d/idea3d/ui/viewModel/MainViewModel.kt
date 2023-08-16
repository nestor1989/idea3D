package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(private val repo: Repo): ViewModel() {
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

    /*init{
        setThings("Relevant")
        setPagination(1)
    }*/

    val fetchThings = searchThing.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                if (searchThing.value=="Relevant" || searchThing.value=="popular" || searchThing.value=="newest"){
                emit(repo.getThingsByNews(it, 1, category.value!!))
                }else emit(repo.getThingsByName(it, 1, category.value!!))
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
                emit(repo.getThingsByNews(searchThing.value!!, page.value!!, category.value!!))
            }else emit(repo.getThingsByName(searchThing.value!!, page.value!!, category.value!!))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
        }
    }


    val fetchNewsList= liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getNews("es"))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


}