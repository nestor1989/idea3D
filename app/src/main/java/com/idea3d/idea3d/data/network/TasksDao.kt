package com.idea3d.idea3d.data.network

import androidx.room.*
import com.idea3d.idea3d.data.model.Task

@Dao
interface TasksDao {
    @Query("SELECT * FROM Task ")
    suspend fun getAllTask(): List<Task>

    @Query("SELECT * FROM Task WHERE [date_begin] LIKE :date")
    suspend fun getByDate(date: String): List<Task>

    @Query("SELECT * FROM Task WHERE [prioritize] = 1")
    suspend fun getUrgent(): List<Task>

    @Query("SELECT * FROM Task WHERE [id_status] LIKE :id_status")
    suspend fun getByStatus(id_status: Int): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}