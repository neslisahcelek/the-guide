package com.example.theguide.data.remote

import com.example.theguide.data.remote.dto.PlaceDto
import com.example.theguide.data.remote.dto.UserIdDto
import com.example.theguide.data.remote.dto.PlaceNameDto
import com.example.theguide.data.remote.dto.UserInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlacesAPI {
    @GET("/user_id/a")
    suspend fun getUserId(): String

    @GET("/place_name/1")
    suspend fun getPlaceName(): PlaceNameDto

    @POST("/add_new_user")
    suspend fun createUser(@Body userInfo: UserInfo): UserIdDto

    @POST("/add_rating")
    suspend fun addRating(
        @Query("user_id") userId: Int,
        @Query("place_id") placeId: Int,
        @Query("rating") rating: Double,
    ): String

    @POST("/recommend")
    suspend fun getRecommendation(
        @Query("uid") userId: Int,
        @Query("k") recommendationLimit: Int = 5,
        @Query("remove_seen") removeSeen: Boolean = true,
    ): List<PlaceDto>
}
