package com.example.theguide.data.mapper

import com.example.theguide.data.remote.response.TopPlacesResponse
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.domain.model.Review

fun TopPlacesResponse.toPlaceModelList(): List<PlaceModel> {
    return recommendations.map { recommendation ->
        PlaceModel(
            id = recommendation.id,
            placeName = recommendation.placeName,
            expectedScore = 0.0f,
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