package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.data.model.Things
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WebService {
    @GET ("search?")
    suspend fun searchThingByNew (@Header("Authorization") value: String,
                                  @Query(value ="sort") searchBy:String) : Things
}