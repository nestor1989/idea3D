package com.idea3d.idea3d.domain.favorites

import com.idea3d.idea3d.data.model.ThingEntity
import com.idea3d.idea3d.data.repo.Repo
import javax.inject.Inject

class GetFavoritesUseCaseImpl @Inject constructor(
    private val repo: Repo
): GetFavoritesUseCase{
    override suspend operator fun invoke(): List<ThingEntity>{
        return repo.getThingsFav()
    }
}