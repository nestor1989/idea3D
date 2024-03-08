package com.idea3d.idea3d.domain.news
import com.idea3d.idea3d.data.model.News
import com.idea3d.idea3d.data.repo.Repo
import javax.inject.Inject

class GetNewsUseCaseImpl @Inject constructor(
    private val repo: Repo
): GetNewsUseCase{
    override suspend operator fun invoke(country: String, key: String): List<News> {
        return repo.getNews(country, key)
    }
}