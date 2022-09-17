package com.idea3d.idea3d.data

import com.idea3d.idea3d.core.Constants
import com.idea3d.idea3d.core.Constants.Companion.THING_KEY
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.core.RetrofitClient
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.model.Things

class DataSource {

        suspend fun getThings (searchBy:String, page:Int): Resource<Things> {
            return Resource.Success(RetrofitClient.thingsService.searchThingByNew(THING_KEY,searchBy, page, Constants.PER_PAGE))
        }

        suspend fun getNews (country:String): Resource<List<News>> {
            return Resource.Success(RetrofitClient.newsService.topHeadLines(country).articles)
        }

        suspend fun getThingByName (searchBy: String, page:Int): Resource<Things>{
            return Resource.Success(RetrofitClient.thingsService.searchThingByName(THING_KEY,searchBy, page, Constants.PER_PAGE))
        }

}