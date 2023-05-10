package com.idea3d.idea3d.data

import com.idea3d.idea3d.utils.Constants
import com.idea3d.idea3d.utils.Constants.Companion.THING_KEY
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.*
import com.idea3d.idea3d.data.network.*
import java.util.*
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val thingsDao: ThingsDao,
    private val newsProvider: NewsProvider,
    private val tasksDao: TasksDao
): DataSource {

    override suspend fun getThings (searchBy:String, page:Int, category: Int): Resource<Things> {
        return Resource.Success(webService.searchThingByNew(THING_KEY,searchBy, page, Constants.PER_PAGE, category))
    }

    override suspend fun getNews (country:String): Resource<List<News>> {
        return Resource.Success(newsProvider.topHeadLines(country).articles)
    }

    override suspend fun getThingByName (searchBy: String, page:Int, category: Int): Resource<Things>{
        return Resource.Success(webService.searchThingByName(THING_KEY,searchBy, page, Constants.PER_PAGE, category))
    }

    override suspend fun insertThing(thingEntity: ThingEntity) {
        thingsDao.addFavoriteThings(thingEntity)
    }

    override suspend fun getFavoriteThings(): Resource<List<ThingEntity>> {
        return Resource.Success(thingsDao.getAllFavoriteThings())
    }

    override suspend fun deleteThing(thingEntity: ThingEntity) {
        thingsDao.deleteFavoriteThing(thingEntity)
    }

    override suspend fun getCategories(): Resource<List<Category>> {
        return Resource.Success(webService.searchCategories(THING_KEY))
    }

    override suspend fun getThingsFromCat(
        page: Int,
        category: Int
    ): Resource<Things> {
        return Resource.Success(webService.searchThingsFromCat(THING_KEY, page, category, Constants.PER_PAGE))
    }

    override suspend fun insertTask(task: Task) {
        tasksDao.addTask(task)
    }

    override suspend fun getAllTask(): Resource<List<Task>> {
        return Resource.Success(tasksDao.getAllTask())
    }

    override suspend fun deleteTask(task: Task) {
        tasksDao.deleteTask(task)
    }

    override suspend fun getByDate(date: String): Resource<List<Task>> {
        return Resource.Success(tasksDao.getByDate(date))
    }

    override suspend fun getUrgent(): Resource<List<Task>> {
        return Resource.Success(tasksDao.getUrgent())
    }

    override suspend fun getByStatus(id_status: Int): Resource<List<Task>> {
        return Resource.Success(tasksDao.getByStatus(id_status))
    }

    override suspend fun updateTask(task: Task) {
        tasksDao.updateTask(task)
    }

    override suspend fun getDateRange(today: String, dateInit: String): Resource<List<Task>> {
        return Resource.Success(tasksDao.getInRange(today, dateInit))
    }


}