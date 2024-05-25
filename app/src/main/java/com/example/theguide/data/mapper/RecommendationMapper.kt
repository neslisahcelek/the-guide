package com.example.theguide.data.mapper

import RecommendationResponse
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.domain.model.Review

fun RecommendationResponse.toPlaceModelList(): List<PlaceModel> {
    return recommendations.map { recommendation ->
        PlaceModel(
            id = recommendation.id,
            placeName = recommendation.placeName,
            expectedScore = recommendation.expectedScore,
            address = recommendation.address,
            openingHours = recommendation.openingHours,
            rating = recommendation.rating,
            types = recommendation.types,
            photos = recommendation.photos,
            reviews = recommendation.reviews.map { review ->
                Review(
                    authorName = review.authorName,
                    text = review.text
                )
            },
            mapsUrl = recommendation.mapsUrl
        )
    }
}
