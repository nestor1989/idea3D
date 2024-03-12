package com.idea3d.idea3d.data.model.mapper

import com.idea3d.idea3d.data.model.works.Task
import com.idea3d.idea3d.data.model.works.TaskDTO

class TaskMapper : Mapper<Task, TaskDTO> {
    override fun mapToUI(type: Task): TaskDTO {
        return TaskDTO(
            id = type.id,
            name = type.name,
            description = type.description,
            id_status = type.id_status,
            status = type.status,
            prioritize = type.prioritize,
            client = type.client,
            id_client = type.id_client,
            price = type.price,
            cost = type.cost,
            thing_photo = type.thing_photo,
            thing_extension = type.thing_extension,
            client_photo = type.client_photo,
            client_ext = type.client_ext,
            date_begin = type.date_begin,
            date_end = type.date_end
        )
    }

    override fun mapToDomain(input: TaskDTO): Task {
        return Task(
            id = input.id,
            name = input.name,
            description = input.description,
            id_status = input.id_status,
            status = input.status,
            prioritize = input.prioritize,
            client = input.client,
            id_client = input.id_client,
            price = input.price,
            cost = input.cost,
            thing_photo = input.thing_photo,
            thing_extension = input.thing_extension,
            client_photo = input.client_photo,
            client_ext = input.client_ext,
            date_begin = input.date_begin,
            date_end = input.date_end
        )
    }
}