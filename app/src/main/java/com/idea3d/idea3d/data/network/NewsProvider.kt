package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.core.Constantes.Companion.API_KEY
import com.idea3d.idea3d.data.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsProvider {

    @GET("everything?q=3D AND impresora&apiKey=$API_KEY")
    suspend fun topHeadLines(
        @Query("language")country:String): Response<NewsApiResponse>
}