package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.*
import java.util.*

interface DataSource {

    suspend fun getThings (searchBy:String, page:Int, category: Int): Resource<Things>

    suspend fun getNews (country:String): Resource<List<News>>

    suspend fun getThingByName (searchBy: String, page:Int, category: Int): Resource<Things>

    suspend fun insertThing (thingEntity: ThingEntity)

    suspend fun getFavoriteThings(): Resource<List<ThingEntity>>

    suspend fun deleteThing (thingEntity: ThingEntity)

    suspend fun getCategories(): Resource<List<Category>>

    suspend fun getThingsFromCat( page:Int, category:Int): Resource<Things>

    suspend fun insertTask (task: Task)

    suspend fun getAllTask(): Resource<List<Task>>

    suspend fun deleteTask(task: Task)

    suspend fun getByDate(date:String): Resource<List<Task>>

    suspend fun getUrgent(): Resource<List<Task>>

    suspend fun getByStatus(id_status: Int): Resource<List<Task>>

    suspend fun updateTask(task:Task)

    suspend fun getDateRange(today: String, dateInit: String): Resource<List<Task>>
}