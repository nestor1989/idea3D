package com.idea3d.idea3d.data.model.mapper

import com.idea3d.idea3d.data.model.home.Things
import com.idea3d.idea3d.data.model.home.ThingsDTO
import javax.inject.Inject

class ThingsMapper@Inject constructor(private val thingMapper: GetThingMapper) : Mapper<Things, ThingsDTO> {
    override fun mapToUI(input: Things): ThingsDTO {
        return ThingsDTO(
                thingsList = input.thingsList.map { thingMapper.mapToUI(it) },
                totalThings = input.totalThings
        )
    }

    override fun mapToDomain(input: ThingsDTO): Things {
        return Things(
            thingsList = input.thingsList.map { thingMapper.mapToDomain(it) },
            totalThings = input.totalThings,
        )
    }
}