package com.idea3d.idea3d.core

import com.google.gson.GsonBuilder
import com.idea3d.idea3d.data.network.NewsProvider
import com.idea3d.idea3d.data.network.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val thingsService: WebService by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.thingiverse.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }

    val newsService by lazy{
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(NewsProvider::class.java)

    }

}