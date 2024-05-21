package com.example.theguide.data.remote

import RecommendationResponse
import com.example.theguide.data.remote.response.CreateUserResponse
import com.example.theguide.data.remote.dto.RatingInfo
import com.example.theguide.data.remote.response.RatingResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlacesAPI {
    @POST("/add_new_user")
    suspend fun createUser(@Query("firebase_id") userId: String): CreateUserResponse

    @POST("/add_rating")
    suspend fun addRating(
        @Body ratingInfo: RatingInfo
    ): RatingResponse

    @GET("/recommend")
    suspend fun getRecommendation(
        @Query("firebase_id") userId: String,
        @Query("k") recommendationLimit: Int = 5,
        @Query("remove_seen") removeSeen: Boolean = true,
    ): RecommendationResponse
}
