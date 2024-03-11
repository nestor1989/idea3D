package com.idea3d.idea3d.data.repository.work

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Task

interface WorkRepository {
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun deleteTask(task: Task)
    suspend fun getByDate(date:String): Resource<List<Task>>
    suspend fun getUrgent(): Resource<List<Task>>
    suspend fun getByStatus(id_status: Int) : Resource<List<Task>>
    suspend fun updateTask(task: Task)
    suspend fun getDateRange(today: String, dateInit:String): Resource<List<Task>>
}