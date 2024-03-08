package com.idea3d.idea3d.domain.things

import com.idea3d.idea3d.data.model.Things
import com.idea3d.idea3d.data.repo.Repo
import javax.inject.Inject

class GetAllThingsUseCaseImpl @Inject constructor(
    private val repo: Repo
): GetAllThingsUseCase {
    override suspend fun invoke(page: Int, category: Int): Things {
        return repo.getThingsFromCat(page, category)
    }

}