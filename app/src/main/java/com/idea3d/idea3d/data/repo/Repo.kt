package com.idea3d.idea3d.data.repo

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Things

interface Repo {
    suspend fun getThingsByNews(searchBy:String, page:Int): Resource<Things>
    suspend fun getNews (country:String): Resource<List<News>>
    suspend fun getThingsByName(searchBy: String, page:Int): Resource<Things>
}