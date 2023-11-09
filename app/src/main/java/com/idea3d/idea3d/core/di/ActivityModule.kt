package com.idea3d.idea3d.core.di

import com.idea3d.idea3d.data.DataSourceImpl
import com.idea3d.idea3d.data.network.DataSource
import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.data.repo.RepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun bindRepoImpl(repoImpl: RepoImpl): Repo

    @Binds
    abstract fun dataSourceImpl(dataSourceImpl: DataSourceImpl): DataSource

}