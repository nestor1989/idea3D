package com.idea3d.idea3d.data.repository.home

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.datasource.local.LocalDataSource
import com.idea3d.idea3d.data.datasource.remote.RemoteDataSource
import com.idea3d.idea3d.data.model.home.Category
import com.idea3d.idea3d.data.model.home.news.News
import com.idea3d.idea3d.data.model.home.ThingEntity
import com.idea3d.idea3d.data.model.home.Things
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
): HomeRepository {
    override suspend fun getThingsByNews(searchBy:String, page:Int, category: Int): Things {
        return remoteDataSource.getThings(searchBy, page, category)
    }

    override suspend fun getNews(country:String, key: String): List<News> {
        return remoteDataSource.getNews(country, key)
    }

    override suspend fun getThingsByName(searchBy: String, page:Int, category: Int): Things {
        return remoteDataSource.getThingByName(searchBy, page, category)
    }

    override suspend fun getCategories(): Resource<List<Category>> {
        return remoteDataSource.getCategories()
    }

    override suspend fun getThingsFromCat( page:Int, category:Int): Things {
        return remoteDataSource.getThingsFromCat( page, category)

    }

    override suspend fun addedThingToFav(thingEntity: ThingEntity) {
        return localDataSource.insertThing(thingEntity)
    }

    override suspend fun getThingsFav(): List<ThingEntity> {
        return localDataSource.getFavoriteThings()
    }

    override suspend fun deleteFavorite(thingEntity: ThingEntity) {
        return localDataSource.deleteThing(thingEntity)
    }
}