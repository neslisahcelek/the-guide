package com.example.theguide.data.remote

import com.example.theguide.data.remote.dto.PlaceDto
import com.example.theguide.data.remote.dto.UserIdDto
import com.example.theguide.data.remote.dto.PlaceNameDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PlacesAPI {
    @GET("places")
    suspend fun getPlaces(
        @Query("userId") userId: String,
    ): List<PlaceDto>

    @GET("/user_id/a")
    suspend fun getUserId(): String

    @GET("/place_name/1")
    suspend fun getPlaceName(): PlaceNameDto

    @GET("/recommend?uid=1&k=1&remove_seen=false")
    suspend fun getRecommendation(): List<Double>

    @POST("/add_new_user")
    suspend fun createUser(@Body userInfo: UserInfo): UserIdDto
}
