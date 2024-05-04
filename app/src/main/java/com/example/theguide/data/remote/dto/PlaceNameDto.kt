package com.example.theguide.data.remote.dto

import com.squareup.moshi.Json

data class PlaceNameDto(
    @Json(name = "place_name") val placeName: String,
)