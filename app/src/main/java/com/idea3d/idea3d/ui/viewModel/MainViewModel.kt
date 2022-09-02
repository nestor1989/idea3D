package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.repo.Repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repo): ViewModel() {
    private val searchThing = MutableLiveData<String>()

    fun setThings(thingBy:String){
        searchThing.value = thingBy
    }

    init{
        setThings("relevant")
    }

    val fetchThings = searchThing.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                if (searchThing.value=="relevant" || searchThing.value=="popular" || searchThing.value=="newest"){
                emit(repo.getThingsByNews(it))
                }else emit(repo.getThingsByName(it))
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