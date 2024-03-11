package com.idea3d.idea3d.di

import com.idea3d.idea3d.data.model.mapper.GetNewsMapper
import com.idea3d.idea3d.data.model.mapper.GetThingMapper
import com.idea3d.idea3d.data.model.mapper.GetThingsMapper
import com.idea3d.idea3d.data.model.mapper.ThingWithCatMapper
import com.idea3d.idea3d.data.model.mapper.ThingsMapper
import com.idea3d.idea3d.data.repository.home.HomeRepository
import com.idea3d.idea3d.data.repository.work.WorkRepository
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCase
import com.idea3d.idea3d.domain.favorites.GetFavoritesUseCaseImpl
import com.idea3d.idea3d.domain.news.GetNewsUseCase
import com.idea3d.idea3d.domain.news.GetNewsUseCaseImpl
import com.idea3d.idea3d.domain.things.GetAllThingsUseCase
import com.idea3d.idea3d.domain.things.GetAllThingsUseCaseImpl
import com.idea3d.idea3d.domain.works.GetAllWoksUseCase
import com.idea3d.idea3d.domain.works.GetAllWorksUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetNewsUseCase(homeRepository: HomeRepository): GetNewsUseCase {
        return GetNewsUseCaseImpl(homeRepository)
    }

    @Provides
    fun provideGetAllThingsUseCase(homeRepository: HomeRepository): GetAllThingsUseCase {
        return GetAllThingsUseCaseImpl(homeRepository)
    }

    @Provides
    fun provideGetFavoritesUseCase(homeRepository: HomeRepository): GetFavoritesUseCase {
        return GetFavoritesUseCaseImpl(homeRepository)
    }

    @Provides
    fun provideGetAllWorksUseCase(workRepository: WorkRepository): GetAllWoksUseCase {
        return GetAllWorksUseCaseImpl(workRepository)
    }

    @Provides
    fun provideGetNewsMapper(): GetNewsMapper {
        return GetNewsMapper()
    }

    @Provides
    fun provideGetThingsMapper(): GetThingsMapper {
        return GetThingsMapper()
    }

    @Provides
    fun provideGetThingMapper(): GetThingMapper {
        return GetThingMapper()
    }

    @Provides
    fun provideThingWithCatMapper(getThingMapper: GetThingMapper): ThingWithCatMapper {
        return ThingWithCatMapper(getThingMapper)
    }

    @Provides
    fun provideThingMapper(getThingMapper: GetThingMapper):ThingsMapper {
        return ThingsMapper(getThingMapper)
    }

}