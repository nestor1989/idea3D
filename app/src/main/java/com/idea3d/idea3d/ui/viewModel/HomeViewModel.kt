package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repo): ViewModel(){
    private val searchThing = MutableLiveData<String>()
    private val page = MutableLiveData<Int>()
    private val category = MutableLiveData<Int>()

    fun fetchCategories() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(repo.getCategories())
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


    fun setPagination(pages:Int){
        page.value = pages
    }

    init{
        setPagination(1)
    }

    fun fetchThings(categoryId: Int) = liveData(Dispatchers.IO) {

            emit(Resource.Loading())
            try {
                emit(repo.getThingsFromCat(page.value!!, categoryId))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
    }


}