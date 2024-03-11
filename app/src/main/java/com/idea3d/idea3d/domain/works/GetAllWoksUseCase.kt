package com.idea3d.idea3d.domain.works

import com.idea3d.idea3d.data.model.works.Task

interface GetAllWoksUseCase {
    suspend operator fun invoke(): List<Task>
}