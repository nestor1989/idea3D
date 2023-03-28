package com.idea3d.idea3d.data.repo

import android.util.Log
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.*
import com.idea3d.idea3d.data.network.DataSource
import javax.inject.Inject

class RepoImpl @Inject constructor(private val dataSource: DataSource):Repo {
    override suspend fun getThingsByNews(searchBy:String, page:Int): Resource<Things> {
        return dataSource.getThings(searchBy, page)
    }

    override suspend fun getNews(country:String): Resource<List<News>> {
        return dataSource.getNews(country)
    }

    override suspend fun getThingsByName(searchBy: String, page:Int): Resource<Things> {
        return dataSource.getThingByName(searchBy, page)
    }

    override suspend fun getCategories(): Resource<List<Category>> {
        return dataSource.getCategories()
    }

    override suspend fun getThingsFromCat( page:Int, category:Int): Resource<Things> {
        Log.d("REPOOO_CATEGORIAA", dataSource.getThingsFromCat( page, category).toString() )
        return dataSource.getThingsFromCat( page, category)

    }

    override suspend fun addedThingToFav(thingEntity: ThingEntity) {
        return dataSource.insertThing(thingEntity)
    }

    override suspend fun getThingsFav(): Resource<List<ThingEntity>> {
        return dataSource.getFavoriteThings()
    }

    override suspend fun deleteFavorite(thingEntity: ThingEntity) {
        return dataSource.deleteThing(thingEntity)
    }
}