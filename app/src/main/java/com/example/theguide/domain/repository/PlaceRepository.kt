package com.example.theguide.domain.repository

import com.example.theguide.data.remote.dto.UserInfo
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.resource.Resource

interface PlaceRepository {
    suspend fun getUserId(): Resource<String>
    suspend fun createUser(info: UserInfo): Resource<String>
    suspend fun getPlaceName(): Resource<String>
    suspend fun addRating(userId: Int, placeId: Int, rating: Double): Resource<String>
    suspend fun getRecommendation(userId: Int, recommendationLimit: Int): Resource<List<Place>>

}