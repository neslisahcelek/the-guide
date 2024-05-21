package com.example.theguide.domain.repository

import Recommendation
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.resource.Resource

interface PlaceRepository {
    suspend fun getUserId(): Resource<String>
    suspend fun createUser(userId: String): Resource<String>
    suspend fun getPlaceName(): Resource<String>
    suspend fun addRating(userId: String, placeId: Int, rating: Double): Resource<String>
    suspend fun getRecommendation(userId: String, recommendationLimit: Int): Resource<List<Recommendation>>

}