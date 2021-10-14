package com.idea3d.idea3d.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.idea3d.idea3d.Constantes.Companion.API_KEY
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.repo.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListScreenViewModel@Inject constructor
    (private val repository: NewsRepository): ViewModel() {

    private val _news= MutableLiveData<List<News>>()

    fun getNews(): LiveData<List<News>>{
        viewModelScope.launch(Dispatchers.IO){
            val news = repository.getNews("es")
            _news.postValue(news)
        }
        return _news
    }




    }