package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.core.Constants.Companion.THING_KEY
import com.idea3d.idea3d.data.model.Thing
import com.idea3d.idea3d.data.model.Things
import retrofit2.http.GET
import retrofit2.http.Header

interface WebService {
    @GET ("search?sort=newest/")
    suspend fun searchThingByNew (@Header("Authorization") value: String = THING_KEY) : Things
}