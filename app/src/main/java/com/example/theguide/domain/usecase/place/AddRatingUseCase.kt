package com.example.theguide.domain.usecase.place

import com.example.theguide.domain.repository.PlaceRepository
import javax.inject.Inject

class AddRatingUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
){
    suspend fun execute(userId: String, placeId: Int, rating: Double) {
        placeRepository.addRating(userId, placeId, rating)
    }
}