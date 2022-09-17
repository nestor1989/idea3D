package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.repo.Repo
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {
    private val searchThing = MutableLiveData<String>()
    private val page = MutableLiveData<Int>()

    fun setThings(thingBy:String){
        searchThing.value = thingBy
    }

    fun setPagination(pages:Int){
        page.value = pages
    }

    init{
        setThings("relevant")
        setPagination(1)
    }

    val fetchThings = searchThing.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                if (searchThing.value=="relevant" || searchThing.value=="popular" || searchThing.value=="newest"){
                emit(repo.getThingsByNews(it, 1))
                }else emit(repo.getThingsByName(it, 1))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchPage = page.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            if (searchThing.value=="relevant" || searchThing.value=="popular" || searchThing.value=="newest"){
                emit(repo.getThingsByNews(searchThing.value!!, page.value!!))
            }else emit(repo.getThingsByName(searchThing.value!!, page.value!!))
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