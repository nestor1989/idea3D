package com.idea3d.idea3d.data.repo

import android.util.Log
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.*
import com.idea3d.idea3d.data.network.DataSource
import java.util.*
import javax.inject.Inject

class RepoImpl @Inject constructor(private val dataSource: DataSource):Repo {
    override suspend fun getThingsByNews(searchBy:String, page:Int, category: Int): Resource<Things> {
        return dataSource.getThings(searchBy, page, category)
    }

    override suspend fun getNews(country:String): Resource<List<News>> {
        return dataSource.getNews(country)
    }

    override suspend fun getThingsByName(searchBy: String, page:Int, category: Int): Resource<Things> {
        return dataSource.getThingByName(searchBy, page, category)
    }

    override suspend fun getCategories(): Resource<List<Category>> {
        return dataSource.getCategories()
    }

    override suspend fun getThingsFromCat( page:Int, category:Int): Resource<Things> {
        Log.d("REPOOO_CATEGORIAA", dataSource.getThingsFromCat( page, category).toString() )
        return dataSource.getThingsFromCat( page, category)

    }

    override suspend fun addedThingToFav(thingEntity: ThingEntity) {
        return dataSource.insertThing(thingEntity)
    }

    override suspend fun getThingsFav(): Resource<List<ThingEntity>> {
        return dataSource.getFavoriteThings()
    }

    override suspend fun deleteFavorite(thingEntity: ThingEntity) {
        return dataSource.deleteThing(thingEntity)
    }

    override suspend fun addTask(task: Task) {
        dataSource.insertTask(task)
    }

    override suspend fun getAllTasks(): Resource<List<Task>> {
        return dataSource.getAllTask()
    }

    override suspend fun deleteTask(task: Task) {
        dataSource.deleteTask(task)
    }

    override suspend fun getByDate(date: String): Resource<List<Task>> {
        return dataSource.getByDate(date)
    }

    override suspend fun getUrgent(): Resource<List<Task>> {
        return dataSource.getUrgent()
    }

    override suspend fun getByStatus(id_status: Int): Resource<List<Task>> {
        return dataSource.getByStatus(id_status)
    }

    override suspend fun updateTask(task: Task) {
        dataSource.updateTask(task)
    }

    override suspend fun getDateRange(today: String, dateInit: String):Resource<List<Task>> {
        return dataSource.getDateRange(today, dateInit)
    }
}