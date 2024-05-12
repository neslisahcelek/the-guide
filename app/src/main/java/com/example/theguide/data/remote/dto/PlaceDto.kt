package com.example.theguide.data.remote.dto

import com.squareup.moshi.Json

data class PlaceDto(
    @field:Json(name = "place_id")
    val placeId: Int,
    @field:Json(name = "google_place_id")
    val googlePlaceId: String,
    @field:Json(name = "place_details")
    val placeDetails: PlaceDetails,
    @field:Json(name = "maps_url")
    val placeUrl: String,
)

data class PlaceDetails(
    @field:Json(name = "name")
    val placeName: String,
    @field:Json(name = "address")
    val address: String,
    @field:Json(name = "opening_hours")
    val openingHours: List<String>,
    val rating: Double,
    @field:Json(name = "types")
    val categories: List<String>,
    @field:Json(name = "photos")
    val photoUrls: List<String>,
    val reviews: List<Review>
)

data class Review(
    @field:Json(name = "author_name")
    val reviewerName: String,
    @field:Json(name = "text")
    val review: String
)
