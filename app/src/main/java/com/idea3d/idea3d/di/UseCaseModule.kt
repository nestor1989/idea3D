package com.idea3d.idea3d.di

import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import com.idea3d.idea3d.domain.news.GetNewsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetNewsUseCase(repo: Repo): GetNewsUseCase {
        return GetNewsUseCaseImpl(repo)
    }
}