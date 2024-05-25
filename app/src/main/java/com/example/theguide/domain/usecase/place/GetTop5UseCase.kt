package com.example.theguide.domain.usecase.place

import com.example.theguide.domain.repository.PlaceRepository
import javax.inject.Inject

class GetTop5UseCase @Inject constructor(
    private val placeRepository: PlaceRepository
){
    suspend fun execute() = placeRepository.getTop5()
}