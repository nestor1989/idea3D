package com.idea3d.idea3d.data.repository.work

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.datasource.local.LocalDataSource
import com.idea3d.idea3d.data.model.works.Task
import javax.inject.Inject

class WorkRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): WorkRepository {
    override suspend fun addTask(task: Task) {
        localDataSource.insertTask(task)
    }

    override suspend fun getAllTasks(): List<Task> {
        return localDataSource.getAllTask()
    }

    override suspend fun deleteTask(task: Task) {
        localDataSource.deleteTask(task)
    }

    override suspend fun getByDate(date: String): Resource<List<Task>> {
        return localDataSource.getByDate(date)
    }

    override suspend fun getUrgent(): Resource<List<Task>> {
        return localDataSource.getUrgent()
    }

    override suspend fun getByStatus(id_status: Int): Resource<List<Task>> {
        return localDataSource.getByStatus(id_status)
    }

    override suspend fun updateTask(task: Task) {
        localDataSource.updateTask(task)
    }

    override suspend fun getDateRange(today: String, dateInit: String): Resource<List<Task>> {
        return localDataSource.getDateRange(today, dateInit)
    }
}