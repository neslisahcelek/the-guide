package com.example.theguide.domain.usecase.place

import com.example.theguide.domain.repository.PlaceRepository
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend fun execute(userId: String, recommendationLimit: Int=5) = placeRepository.getRecommendation(
        userId = userId,
        recommendationLimit = recommendationLimit
    )
}