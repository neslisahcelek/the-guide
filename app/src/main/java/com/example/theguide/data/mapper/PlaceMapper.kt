package com.example.theguide.data.mapper

import com.example.theguide.data.remote.dto.PlaceDto
import com.example.theguide.domain.model.Place

fun List<PlaceDto>.toPlaceModel() = map { placeDto: PlaceDto ->
    Place(
        id = placeDto.id,
        name = "name",
        description = "description",
        rating = placeDto.rating,
        image = placeDto.image
    )
}