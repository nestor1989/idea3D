package com.idea3d.idea3d.di

import com.idea3d.idea3d.data.datasource.local.LocalDataSource
import com.idea3d.idea3d.data.datasource.local.LocalDataSourceImpl
import com.idea3d.idea3d.data.datasource.remote.RemoteDataSource
import com.idea3d.idea3d.data.datasource.remote.RemoteDataSourceImpl
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.data.repository.home.HomeRepositoryImpl
import com.idea3d.idea3d.data.repository.work.WorkRepository
import com.idea3d.idea3d.data.repository.work.WorkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindHomeRepositoryImpl(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindWorkRepositoryImpl(workRepositoryImpl: WorkRepositoryImpl): WorkRepository

    @Binds
    abstract fun remoteDataSourceImpl(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    abstract fun localDataSourceImpl(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

}