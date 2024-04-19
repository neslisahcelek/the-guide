package com.example.theguide.domain.repository

import com.example.theguide.domain.model.Place
import com.example.theguide.domain.util.Resource

interface PlaceRepository {
    suspend fun getPlaces(): Resource<List<Place>>
}