package com.example.theguide.domain.usecase.place

import com.example.theguide.domain.repository.PlaceRepository
import javax.inject.Inject

class GetTopPlacesUseCase @Inject constructor(
    private val placeRepository: PlaceRepository
){
    suspend fun execute() = placeRepository.getTopPlaces()
}