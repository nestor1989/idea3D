package com.idea3d.idea3d.domain.news
import com.idea3d.idea3d.data.model.home.news.News
import com.idea3d.idea3d.data.repository.home.HomeRepository
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
): GetNewsUseCase{
    override suspend operator fun invoke(country: String, key: String): List<News> {
        return homeRepository.getNews(country, key)
    }
}