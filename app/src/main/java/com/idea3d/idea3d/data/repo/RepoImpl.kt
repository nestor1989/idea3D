package com.idea3d.idea3d.data.repo

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.model.Things
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
}