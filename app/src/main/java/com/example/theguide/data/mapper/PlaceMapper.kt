package com.example.theguide.data.mapper

import com.example.theguide.data.remote.dto.PlaceDto
import com.example.theguide.domain.model.Place

fun List<PlaceDto>.toPlaceModel() = map { placeDto: PlaceDto ->
    Place(
        id = placeDto.placeId,
        name = placeDto.placeDetails.placeName,
        details = placeDto.placeDetails,
        url = placeDto.placeUrl,
        rating = placeDto.placeDetails.rating,
        imageUrl = placeDto.placeDetails.photoUrls.first()
    )
}