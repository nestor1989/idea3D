package com.idea3d.idea3d.data.datasource.local

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.data.model.home.ThingEntity
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
        thingsDao.deleteFavoriteThing(thingEntity.id)
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

    override suspend fun getByDate(date: String): List<Task> {
        return tasksDao.getByDate(date)
    }

    override suspend fun getUrgent(): List<Task> {
        return tasksDao.getUrgent()
    }

    override suspend fun getByStatus(id_status: Int): List<Task> {
        return tasksDao.getByStatus(id_status)
    }

    override suspend fun updateTask(task: Task) {
        tasksDao.updateTask(task)
    }

    override suspend fun getDateRange(today: String, dateInit: String): List<Task> {
        return tasksDao.getInRange(today, dateInit)
    }

}