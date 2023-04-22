package com.idea3d.idea3d.data.repo

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.*

interface Repo {
    suspend fun getThingsByNews(searchBy:String, page:Int, category: Int): Resource<Things>
    suspend fun getNews (country:String): Resource<List<News>>
    suspend fun getThingsByName(searchBy: String, page:Int, category: Int): Resource<Things>
    suspend fun getCategories(): Resource<List<Category>>
    suspend fun getThingsFromCat(page:Int, category:Int): Resource<Things>
    suspend fun addedThingToFav(thingEntity: ThingEntity)
    suspend fun getThingsFav(): Resource<List<ThingEntity>>
    suspend fun deleteFavorite(thingEntity: ThingEntity)
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): Resource<List<Task>>
    suspend fun deleteTask(task: Task)
    suspend fun getByDate(date:String): Resource<List<Task>>
    suspend fun getUrgent(): Resource<List<Task>>
    suspend fun getByStatus(id_status: Int) : Resource<List<Task>>
}