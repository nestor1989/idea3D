package com.idea3d.idea3d.di

import com.idea3d.idea3d.data.repo.Repo
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCase
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCaseImpl
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import com.idea3d.idea3d.domain.news.GetNewsUseCaseImpl
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCaseImpl
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

    @Provides
    fun provideGetAllThingsUseCase(repo: Repo): GetAllThingsUseCase {
        return GetAllThingsUseCaseImpl(repo)
    }

    @Provides
    fun provideGetFavoritesUseCase(repo: Repo): GetFavoritesUseCase {
        return GetFavoritesUseCaseImpl(repo)
    }
}