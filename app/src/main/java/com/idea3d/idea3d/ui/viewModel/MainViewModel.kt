package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.mapper.GetThingMapper
import com.idea3d.idea3d.data.model.mapper.GetThingsMapper
import com.idea3d.idea3d.data.model.mapper.ThingWithCatMapper
import com.idea3d.idea3d.data.model.mapper.ThingsMapper
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel@Inject constructor(
    private val homeRepository: HomeRepository,
    private val thingsMapper: ThingsMapper
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
                if (searchThing.value==Constants.RELEVANT || searchThing.value==Constants.POPULAR || searchThing.value==Constants.NEWEST){
                    val result = homeRepository.getThingsByNews(it, 1, category.value!!)
                    emit(Resource.Success(thingsMapper.mapToUI(result)))
                }else{
                    val result = homeRepository.getThingsByName(it, 1, category.value!!)
                    emit(Resource.Success(thingsMapper.mapToUI(result)))
                }
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }

    val fetchPage = page.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            if (searchThing.value==Constants.RELEVANT || searchThing.value==Constants.POPULAR || searchThing.value==Constants.NEWEST){
                val result = homeRepository.getThingsByNews(searchThing.value!!, page.value!!, category.value!!)
                emit(Resource.Success(thingsMapper.mapToUI(result)))
            }else{
                val result = homeRepository.getThingsByName(searchThing.value!!, page.value!!, category.value!!)
                emit(Resource.Success(thingsMapper.mapToUI(result)))
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
        }
    }


}