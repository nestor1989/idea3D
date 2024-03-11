package com.idea3d.idea3d.domain.things

import com.idea3d.idea3d.data.model.Things
import com.idea3d.idea3d.data.repository.home.HomeRepository
import javax.inject.Inject

class GetAllThingsUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
): GetAllThingsUseCase {
    override suspend fun invoke(page: Int, category: Int): Things {
        return homeRepository.getThingsFromCat(page, category)
    }

}