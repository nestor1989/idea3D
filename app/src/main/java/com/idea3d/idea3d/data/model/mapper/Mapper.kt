package com.idea3d.idea3d.data.model.mapper

interface Mapper<I, O> {
    fun mapToUI(input: I): O
    fun mapToDomain(input: O): I
}