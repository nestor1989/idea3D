package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.repo.Repo
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repo: Repo): ViewModel() {
    private val searchThing = MutableLiveData<String>()

    fun setThings(thingBy:String){
        searchThing.value = thingBy
    }

    init{
        setThings("newest/")
    }

    val fetchThings = searchThing.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(repo.getThingsByNews(it))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

}