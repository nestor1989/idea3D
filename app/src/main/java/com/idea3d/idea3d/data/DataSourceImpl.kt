package com.idea3d.idea3d.data

import com.idea3d.idea3d.core.Constants
import com.idea3d.idea3d.core.Constants.Companion.THING_KEY
import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.Category
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.model.Things
import com.idea3d.idea3d.data.network.DataSource
import com.idea3d.idea3d.data.network.NewsProvider
import com.idea3d.idea3d.data.network.ThingsDao
import com.idea3d.idea3d.data.network.WebService
import javax.inject.Inject

class DataSourceImpl @Inject constructor(
    private val webService: WebService,
    private val thingsDao: ThingsDao,
    private val newsProvider: NewsProvider
): DataSource {

    override suspend fun getThings (searchBy:String, page:Int, category: Int): Resource<Things> {
        return Resource.Success(webService.searchThingByNew(THING_KEY,searchBy, page, Constants.PER_PAGE, category))
    }

    override suspend fun getNews (country:String): Resource<List<News>> {
        return Resource.Success(newsProvider.topHeadLines(country).articles)
    }

    override suspend fun getThingByName (searchBy: String, page:Int, category: Int): Resource<Things>{
        return Resource.Success(webService.searchThingByName(THING_KEY,searchBy, page, Constants.PER_PAGE, category))
    }

    override suspend fun insertThing(thingEntity: ThingEntity) {
        thingsDao.addFavoriteThings(thingEntity)
    }

    override suspend fun getFavoriteThings(): Resource<List<ThingEntity>> {
        return Resource.Success(thingsDao.getAllFavoriteThings())
    }

    override suspend fun deleteThing(thingEntity: ThingEntity) {
        thingsDao.deleteFavoriteThing(thingEntity)
    }

    override suspend fun getCategories(): Resource<List<Category>> {
        return Resource.Success(webService.searchCategories(THING_KEY))
    }

    override suspend fun getThingsFromCat(
        page: Int,
        category: Int
    ): Resource<Things> {
        return Resource.Success(webService.searchThingsFromCat(THING_KEY, page, category, Constants.PER_PAGE))
    }

}