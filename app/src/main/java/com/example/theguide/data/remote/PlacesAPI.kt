package com.example.theguide.data.remote

import RecommendationResponse
import com.example.theguide.data.remote.response.BaseResponse
import com.example.theguide.data.remote.dto.RatingInfo
import com.example.theguide.data.remote.dto.RecommendInfo
import com.example.theguide.data.remote.response.RatingResponse
import com.example.theguide.data.remote.response.TopPlacesResponse
import com.example.theguide.data.remote.response.WishListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlacesAPI {
    @POST("/add_new_user")
    suspend fun createUser(@Query("firebase_id") userId: String): BaseResponse

    @POST("/add_rating")
    suspend fun addRating(
        @Body ratingInfo: RatingInfo
    ): RatingResponse

    @POST("/recommend")
    suspend fun getRecommendation(
        @Body recommendInfo: RecommendInfo
    ): RecommendationResponse

    @POST("/add_favorite")
    suspend fun addToWishList(
        @Query("user_id") userId: String,
        @Query("place_id") placeId: Int
    ): BaseResponse

    @POST("/remove_favorite")
    suspend fun removeFromWishList(
        @Query("user_id") userId: String,
        @Query("place_id") placeId: Int
    ): BaseResponse

    @GET("/get_favorites")
    suspend fun getWishList(
        @Query("user_id") userId: String
    ): WishListResponse

    @GET("/first_5_places")
    suspend fun getTop5(): TopPlacesResponse
}
