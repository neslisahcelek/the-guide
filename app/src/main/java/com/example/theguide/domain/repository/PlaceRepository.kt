package com.example.theguide.domain.repository

import com.example.theguide.domain.model.Place

interface PlaceRepository {
    suspend fun getPlaces(): List<Place>
}