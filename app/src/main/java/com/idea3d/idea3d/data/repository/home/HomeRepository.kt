package com.idea3d.idea3d.data.repository.home

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.*

interface HomeRepository {
    suspend fun getThingsByNews(searchBy:String, page:Int, category: Int): Resource<Things>
    suspend fun getNews (country:String, key: String): List<News>
    suspend fun getThingsByName(searchBy: String, page:Int, category: Int): Resource<Things>
    suspend fun getCategories(): Resource<List<Category>>
    suspend fun getThingsFromCat(page:Int, category:Int): Things
    suspend fun addedThingToFav(thingEntity: ThingEntity)
    suspend fun getThingsFav(): List<ThingEntity>
    suspend fun deleteFavorite(thingEntity: ThingEntity)
}