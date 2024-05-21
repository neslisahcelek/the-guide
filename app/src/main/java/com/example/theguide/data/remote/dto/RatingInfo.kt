package com.example.theguide.data.remote.dto

import com.squareup.moshi.Json

data class RatingInfo (
    @field:Json(name = "firebase_id")
    val userId: String,
    @field:Json(name = "place_id")
    val placeId: Int,
    @field:Json(name = "rating")
    val rating: Double
)