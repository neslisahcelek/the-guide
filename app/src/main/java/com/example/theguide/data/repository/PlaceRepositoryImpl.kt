package com.example.theguide.data.repository

import com.example.theguide.data.mapper.toPlaceModel
import com.example.theguide.data.remote.PlaceDto
import com.example.theguide.data.remote.PlacesAPI
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.repository.PlaceRepository

class PlaceRepositoryImpl constructor(
    private val placesAPI: PlacesAPI
) : PlaceRepository{
    override suspend fun getPlaces(): List<Place> {
        try {
            return placesAPI.getPlaces("id").toPlaceModel()
        } catch (e: Exception) {
            throw e
        }
    }
}