package com.example.theguide.data.repository

import com.example.theguide.data.mapper.toPlaceModel
import com.example.theguide.data.remote.PlaceDto
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.repository.PlaceRepository
import com.example.theguide.domain.util.Resource

class PlaceRepositoryImpl constructor(
    private val placesAPI: PlacesAPI
) : PlaceRepository {
    override suspend fun getPlaces(): Resource<List<Place>> {
        return try {
            Resource.Success(
                placesAPI.getPlaces("id").toPlaceModel()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error("An error occurred")
        }
    }
}