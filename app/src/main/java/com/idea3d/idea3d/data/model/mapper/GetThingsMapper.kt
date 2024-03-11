package com.idea3d.idea3d.data.model.mapper

import com.idea3d.idea3d.data.model.home.ThingDTO
import com.idea3d.idea3d.data.model.home.ThingEntity

class GetThingsMapper :Mapper<List<ThingEntity>, List<ThingDTO>> {
    override fun mapToUI(input: List<ThingEntity>): List<ThingDTO> {
        return input.map { thing ->
            ThingDTO(
                id = thing.id,
                name = thing.name,
                image = thing.image,
                url = thing.url,
                favorite = thing.favorite
            )
        }
    }

    override fun mapToDomain(input: List<ThingDTO>): List<ThingEntity> {
        return input.map { thingDTO ->
            ThingEntity(
                id = thingDTO.id,
                name = thingDTO.name,
                image = thingDTO.image,
                url = thingDTO.url,
                favorite = thingDTO.favorite
            )
        }
    }
}