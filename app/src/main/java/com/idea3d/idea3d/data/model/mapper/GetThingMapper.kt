package com.idea3d.idea3d.data.model.mapper

import com.idea3d.idea3d.data.model.home.ThingDTO
import com.idea3d.idea3d.data.model.home.ThingEntity

class GetThingMapper :Mapper<ThingEntity, ThingDTO> {
    override fun mapToUI(input: ThingEntity): ThingDTO {
        return ThingDTO(
                id = input.id,
                name = input.name,
                image = input.image,
                url = input.url,
                favorite = input.favorite
            )
    }

    override fun mapToDomain(input: ThingDTO): ThingEntity {
        return ThingEntity(
            id = input.id,
            name = input.name,
            image = input.image,
            url = input.url,
            favorite = input.favorite
        )
    }
}