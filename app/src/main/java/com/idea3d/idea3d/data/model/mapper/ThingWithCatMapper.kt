package com.idea3d.idea3d.data.model.mapper

import com.idea3d.idea3d.data.model.home.ThingWithCat
import com.idea3d.idea3d.data.model.home.ThingWithCatDTO
import javax.inject.Inject

class ThingWithCatMapper@Inject constructor(private val thingMapper: GetThingMapper) : Mapper<List<ThingWithCat>, List<ThingWithCatDTO>> {
    override fun mapToUI(typeList: List<ThingWithCat>): List<ThingWithCatDTO> {
        return typeList.map { type ->
            ThingWithCatDTO(
                things = type.things.map { thingMapper.mapToUI(it) },
                catId = type.catId,
                catName = type.catName
            )
        }
    }

    override fun mapToDomain(inputList: List<ThingWithCatDTO>): List<ThingWithCat> {
        return inputList.map { input ->
            ThingWithCat(
                things = input.things.map { thingMapper.mapToDomain(it) },
                catId = input.catId,
                catName = input.catName
            )
        }
    }
}