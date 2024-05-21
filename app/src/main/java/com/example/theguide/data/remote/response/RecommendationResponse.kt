import com.squareup.moshi.Json

data class RecommendationResponse(
    @field:Json(name = "recommendations")
    val recommendations: List<Recommendation>
)

data class Recommendation(
    @field:Json(name = "name")
    val placeName: String,
    @field:Json(name = "address")
    val address: String,
    @field:Json(name = "opening_hours")
    val openingHours: List<String>,
    @field:Json(name = "rating")
    val rating: Double,
    @field:Json(name = "types")
    val types: List<String>,
    @field:Json(name = "photos")
    val photos: List<String>,
    @field:Json(name = "reviews")
    val reviews: List<Review>,
    @field:Json(name = "maps_url")
    val mapsUrl: String
)

data class Review(
    @field:Json(name = "author_name")
    val authorName: String,
    @field:Json(name = "text")
    val text: String
)
