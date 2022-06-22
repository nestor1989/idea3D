package com.idea3d.idea3d.data.repo

import com.idea3d.idea3d.core.Resource
import com.idea3d.idea3d.data.DataSource
import com.idea3d.idea3d.data.model.Thing

class RepoImpl (private val dataSource: DataSource):Repo {
    override suspend fun getThingsByNews(searchBy:String): Resource<List<Thing>> {
        return dataSource.getThings(searchBy)
    }
}