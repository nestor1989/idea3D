package com.idea3d.idea3d.domain.news

import com.idea3d.idea3d.data.model.home.news.News

interface GetNewsUseCase {
    suspend operator fun invoke(country: String, key: String): List<News>
}