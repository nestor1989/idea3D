package com.idea3d.idea3d.data.datasource.local

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.datasource.local.LocalDataSource
import com.idea3d.idea3d.data.model.Task
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.network.TasksDao
import com.idea3d.idea3d.data.network.ThingsDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val thingsDao: ThingsDao,
    private val tasksDao: TasksDao
): LocalDataSource {
    override suspend fun insertThing(thingEntity: ThingEntity) {
        thingsDao.addFavoriteThings(thingEntity)
    }

    override suspend fun getFavoriteThings(): List<ThingEntity> {
        return thingsDao.getAllFavoriteThings()
    }

    override suspend fun deleteThing(thingEntity: ThingEntity) {
        thingsDao.deleteFavoriteThing(thingEntity)
    }

    override suspend fun insertTask(task: Task) {
        tasksDao.addTask(task)
    }

    override suspend fun getAllTask(): List<Task> {
        return tasksDao.getAllTask()
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