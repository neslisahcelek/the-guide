package com.example.theguide.data.remote.dto

import com.example.theguide.domain.model.Place
import com.squareup.moshi.Json

data class PlaceDto (
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "rating")
    val rating: Double,
    @field:Json(name = "image")
    val image: Int
)