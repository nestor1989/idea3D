package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.model.Things

interface DataSource {

    suspend fun getThings (searchBy:String, page:Int): Resource<Things>

    suspend fun getNews (country:String): Resource<List<News>>

    suspend fun getThingByName (searchBy: String, page:Int): Resource<Things>

    suspend fun insertThing (thingEntity: ThingEntity)

    suspend fun getFavoriteThings(): Resource<List<ThingEntity>>

    suspend fun deleteThing (thingEntity: ThingEntity)
}