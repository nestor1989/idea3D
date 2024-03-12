package com.idea3d.idea3d.data.repository.work

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.works.Task

interface WorkRepository {
    suspend fun addTask(task: Task)
    suspend fun getAllTasks(): List<Task>
    suspend fun deleteTask(task: Task)
    suspend fun getByDate(date:String): List<Task>
    suspend fun getUrgent(): List<Task>
    suspend fun getByStatus(id_status: Int) : List<Task>
    suspend fun updateTask(task: Task)
    suspend fun getDateRange(today: String, dateInit:String): List<Task>
}