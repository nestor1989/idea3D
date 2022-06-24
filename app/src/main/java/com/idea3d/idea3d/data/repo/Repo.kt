package com.idea3d.idea3d.data.repo

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Thing

interface Repo {
    suspend fun getThingsByNews(searchBy:String): Resource<List<Thing>>
    suspend fun getNews (country:String): Resource<List<News>>
}