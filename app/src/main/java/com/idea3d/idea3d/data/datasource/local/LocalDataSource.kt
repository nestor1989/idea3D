package com.idea3d.idea3d.data.datasource.local

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.data.model.home.ThingEntity

interface LocalDataSource {
    suspend fun insertThing (thingEntity: ThingEntity)

    suspend fun getFavoriteThings(): List<ThingEntity>

    suspend fun deleteThing (thingEntity: ThingEntity)
    suspend fun insertTask (task: Task)

    suspend fun getAllTask(): List<Task>

    suspend fun deleteTask(task: Task)

    suspend fun getByDate(date:String): Resource<List<Task>>

    suspend fun getUrgent(): Resource<List<Task>>

    suspend fun getByStatus(id_status: Int): Resource<List<Task>>

    suspend fun updateTask(task: Task)

    suspend fun getDateRange(today: String, dateInit: String): Resource<List<Task>>
}