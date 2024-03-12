package com.idea3d.idea3d.data.model.works

import com.idea3d.idea3d.data.model.mapper.Mapper
import com.idea3d.idea3d.data.model.mapper.TaskMapper
import javax.inject.Inject

class GetAllTaskMapper@Inject constructor(private val taskMapper: TaskMapper) : Mapper<List<Task>, List<TaskDTO>> {
    override fun mapToUI(typeList: List<Task>): List<TaskDTO> {
        return typeList.map { taskMapper.mapToUI(it) }
    }

    override fun mapToDomain(inputList: List<TaskDTO>): List<Task> {
        return inputList.map { taskMapper.mapToDomain(it) }
    }
}