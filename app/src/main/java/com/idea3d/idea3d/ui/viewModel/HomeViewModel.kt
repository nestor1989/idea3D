package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.*
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.home.ThingDTO
import com.idea3d.idea3d.data.model.home.ThingEntity
import com.idea3d.idea3d.data.model.mapper.GetNewsMapper
import com.idea3d.idea3d.data.model.mapper.GetThingMapper
import com.idea3d.idea3d.data.model.mapper.GetThingsMapper
import com.idea3d.idea3d.data.model.mapper.ThingWithCatMapper
import com.idea3d.idea3d.data.model.mapper.ThingsMapper
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCase
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val getAllThingsUseCase: GetAllThingsUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val getNewsUseCase: GetNewsUseCase,
    private val getNewsMapper: GetNewsMapper,
    private val getThingMapper: GetThingMapper,
    private val getThingsMapper: GetThingsMapper,
    private val thingsMapper: ThingsMapper
    ): ViewModel(){
    val page = MutableLiveData<Int>()

    fun fetchCategories() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            emit(homeRepository.getCategories())
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
                val result = getAllThingsUseCase(page.value!!, categoryId)
                emit(Resource.Success(thingsMapper.mapToUI(result)))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
    }

    fun addedToFavorite (thing: ThingDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            homeRepository.addedThingToFav(getThingMapper.mapToDomain(thing))
        }
    }

    fun deleteFavorite (thing: ThingDTO) {
        CoroutineScope(Dispatchers.IO).launch {
            homeRepository.deleteFavorite(getThingMapper.mapToDomain(thing))
        }
    }

    fun getFavorites() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getFavoritesUseCase()
            emit(Resource.Success(getThingsMapper.mapToUI(result)))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }

    fun fetchNewsList(country: String, key: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val result = getNewsUseCase(country, key)
            emit(Resource.Success(getNewsMapper.mapToUI(result)))
        }catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }


}