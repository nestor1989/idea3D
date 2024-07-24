package com.idea3d.idea3d.data.network

import com.idea3d.idea3d.utils.Constants.Companion.API_KEY
import com.idea3d.idea3d.data.model.home.news.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsProvider {

    @GET("everything?apiKey=$API_KEY")
    suspend fun topHeadLines(
        @Query("language") country: String,
        @Query("q") q: String
    ): NewsApiResponse
}