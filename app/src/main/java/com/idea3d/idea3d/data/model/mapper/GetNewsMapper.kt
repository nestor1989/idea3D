package com.idea3d.idea3d.data.model.mapper

import com.idea3d.idea3d.data.model.home.news.News
import com.idea3d.idea3d.data.model.home.news.NewsDTO

class GetNewsMapper : Mapper<List<News>, List<NewsDTO>> {
    override fun mapToUI(input: List<News>): List<NewsDTO> {
        return input.map { news ->
            NewsDTO(
                title = news.title,
                content = news.content,
                url = news.url,
                author = news.author,
                urlToImage = news.urlToImage
            )
        }
    }
    override fun mapToDomain(input: List<NewsDTO>): List<News> {
        return input.map { newsDTO ->
            News(
                title = newsDTO.title,
                content = newsDTO.content,
                url = newsDTO.url,
                author = newsDTO.author,
                urlToImage = newsDTO.urlToImage
            )
        }
    }
}