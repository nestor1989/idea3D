package com.idea3d.idea3d.domain.works

import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.data.repository.work.WorkRepository
import javax.inject.Inject

class GetAllWorksUseCaseImpl @Inject constructor(
        private val workRepository: WorkRepository
    ) : GetAllWoksUseCase {
        override suspend operator fun invoke(): List<Task> {
            return workRepository.getAllTasks()
        }

}