package com.idea3d.idea3d.domain.favorites

import com.idea3d.idea3d.data.model.home.ThingEntity
import com.idea3d.idea3d.data.repository.home.HomeRepository
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
): GetFavoritesUseCase{
    override suspend operator fun invoke(): List<ThingEntity>{
        return homeRepository.getThingsFav()
    }
}