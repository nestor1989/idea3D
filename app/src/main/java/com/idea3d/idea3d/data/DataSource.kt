package com.idea3d.idea3d.data

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.core.RetrofitClient
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.model.Things

class DataSource {

        suspend fun getThings (): Resource<List<Thing>> {
            return Resource.Success(RetrofitClient.thingsService.searchThingByNew().thingsList)
        }
}