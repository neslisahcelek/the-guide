package com.example.theguide.domain.model

data class PlaceModel(
    val id: Int = 0,
    val placeName: String = "",
    val expectedScore: Float = 0.0f,
    val address: String = "",
    val openingHours: List<String> = emptyList(),
    val rating: Double = 0.0,
    val types: List<String> = emptyList(),
    val photos: List<String> = emptyList(),
    val reviews: List<Review> = emptyList(),
    val mapsUrl: String = "",
    val userRating: Double = 0.0,
    val isUserRated: Boolean = false
) {
    fun toMap(userRating: Double = 0.0, isUserRated: Boolean = false): Any {
        return mapOf(
            "id" to id,
            "placeName" to placeName,
            "expectedScore" to expectedScore,
            "address" to address,
            "openingHours" to openingHours,
            "rating" to rating,
            "types" to types,
            "photos" to photos,
            "reviews" to emptyList<Review>(),
            "mapsUrl" to mapsUrl,
            "isUserRated" to isUserRated,
            "userRating" to userRating
        )
    }
}
data class Review(
    val authorName: String = "",
    val text: String = ""
)