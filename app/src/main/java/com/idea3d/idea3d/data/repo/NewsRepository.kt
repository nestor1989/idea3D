package com.idea3d.idea3d.data.repo

import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.network.NewsProvider
import javax.inject.Inject

interface NewsRepository {
    suspend fun getNews(country:String): List<News>
    fun getNew(title:String):News
}

class NewsRepositoryImp @Inject constructor(
    private val newsProvider:NewsProvider):NewsRepository{

    private var news: List<News> = emptyList()

    override suspend fun getNews(country: String): List<News> {
        val apiResponse = newsProvider.topHeadLines(country).body()
        news= apiResponse?.articles?: emptyList()
        return news
    }

    override fun getNew(title: String): News =
        news.first {
        it.title==title
    }

}
