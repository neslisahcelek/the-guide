package com.example.theguide.domain.model

data class PlaceModel(
    val id: Int,
    val placeName: String,
    val expectedScore: Double,
    val address: String,
    val openingHours: List<String>,
    val rating: Double,
    val types: List<String>,
    val photos: List<String>,
    val reviews: List<Review>,
    val mapsUrl: String
)

data class Review(
    val authorName: String,
    val text: String
)