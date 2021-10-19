package com.idea3d.idea3d.core.di

import com.idea3d.idea3d.data.network.NewsProvider
import com.idea3d.idea3d.data.repo.NewsRepository
import com.idea3d.idea3d.data.repo.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


    @Module
    @InstallIn(SingletonComponent::class)
    class RepositoryModule {

        @Provides
        @Singleton
        fun providerNewsRepository(provider: NewsProvider): NewsRepository =
            NewsRepositoryImp(provider)
    }
