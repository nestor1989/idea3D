package com.idea3d.idea3d.data.datasource.remote

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.home.Category
import com.idea3d.idea3d.data.model.home.news.News
import com.idea3d.idea3d.data.model.home.Things

interface RemoteDataSource {

    suspend fun getThings (searchBy:String, page:Int, category: Int): Resource<Things>

    suspend fun getNews (country:String, key: String): List<News>

    suspend fun getThingByName (searchBy: String, page:Int, category: Int): Resource<Things>

    suspend fun getCategories(): Resource<List<Category>>

    suspend fun getThingsFromCat( page:Int, category:Int): Things
}