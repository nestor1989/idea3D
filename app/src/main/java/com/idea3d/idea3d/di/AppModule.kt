package com.idea3d.idea3d.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.idea3d.idea3d.core.AppDataBase
import com.idea3d.idea3d.data.network.NewsProvider
import com.idea3d.idea3d.data.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideWebService(okHttpClient: OkHttpClient): WebService {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.thingiverse.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        return retrofit.create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideNewsService(): NewsProvider {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        return retrofit.create(NewsProvider::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDataBase::class.java,"tabla cosas"
    ).build()

    @Singleton
    @Provides
    fun provideThingsDao(db: AppDataBase) = db.thingsDao()

    @Singleton
    @Provides
    fun provideTasksDao(db: AppDataBase) = db.tasksDao()
}