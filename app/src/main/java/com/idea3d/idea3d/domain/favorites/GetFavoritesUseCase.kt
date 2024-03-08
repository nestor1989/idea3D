package com.idea3d.idea3d.domain.favorites

import com.idea3d.idea3d.data.model.ThingEntity

interface GetFavoritesUseCase {
    suspend operator fun invoke(): List<ThingEntity>
}