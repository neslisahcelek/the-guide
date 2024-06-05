package com.example.theguide.domain.usecase.place

import com.example.theguide.domain.repository.PlaceRepository
import javax.inject.Inject

class GetRecommendationUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend fun execute(
        userId: String,
        recommendationLimit: Int = 15,
        districtList: List<String> = listOf()
    ) = placeRepository.getRecommendation(
        userId = userId,
        recommendationLimit = recommendationLimit,
        districtList = districtList
    )
}