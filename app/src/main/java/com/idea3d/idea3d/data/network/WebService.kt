package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.data.model.Category
import com.idea3d.idea3d.data.model.Things
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET ("search?")
    suspend fun searchThingByNew (  @Header("Authorization") value: String,
                                    @Query(value ="sort") searchBy:String,
                                    @Query(value="page") page:Int,
                                    @Query(value="per_page") per_page:Int,
                                    @Query(value = "category_id") category_id:Int) : Things

    @GET("search/{searchBy}/")
    suspend fun searchThingByName ( @Header("Authorization") value: String,
                                    @Path("searchBy") searchBy: String,
                                    @Query(value="page") page:Int,
                                    @Query(value="per_page") per_page:Int,
                                    @Query(value = "category_id") category_id:Int) : Things

    @GET ("categories")
    suspend fun searchCategories (  @Header("Authorization") value: String): List<Category>

    @GET ("search?/")
    suspend fun searchThingsFromCat (   @Header("Authorization") value: String,
                                        @Query(value="page") page:Int,
                                        @Query(value = "category_id") category: Int,
                                        @Query(value="per_page") per_page:Int) : Things
}

