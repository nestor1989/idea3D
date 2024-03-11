package com.idea3d.idea3d.data.datasource.remote

import com.idea3d.idea3d.utils.Constants
import com.idea3d.idea3d.utils.Constants.Companion.THING_KEY
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.datasource.remote.RemoteDataSource
import com.idea3d.idea3d.data.model.*
import com.idea3d.idea3d.data.network.*
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val newsProvider: NewsProvider
): RemoteDataSource {

    override suspend fun getThings (searchBy:String, page:Int, category: Int): Resource<Things> {
        return Resource.Success(webService.searchThingByNew(THING_KEY,searchBy, page, Constants.PER_PAGE, category, "now-3M"))
    }

    override suspend fun getNews (country:String, key: String): List<News> {
        return newsProvider.topHeadLines(country, key).articles
    }

    override suspend fun getThingByName (searchBy: String, page:Int, category: Int): Resource<Things>{
        return Resource.Success(webService.searchThingByName(THING_KEY,searchBy, page, Constants.PER_PAGE, category))
    }

    override suspend fun getCategories(): Resource<List<Category>> {
        return Resource.Success(webService.searchCategories(THING_KEY))
    }

    override suspend fun getThingsFromCat(
        page: Int,
        category: Int
    ): Things {
        return webService.searchThingsFromCat(THING_KEY, page, category, Constants.PER_PAGE, "now-3M")
    }
}