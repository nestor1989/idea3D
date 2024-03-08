package com.idea3d.idea3d.domain.things

import com.idea3d.idea3d.data.model.Things

interface GetAllThingsUseCase {
    suspend operator fun invoke(page:Int, category:Int): Things
}