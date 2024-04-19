package com.example.theguide.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesAPI {
    @GET("places")
    suspend fun getPlaces(
        @Query("id") id: String,
    ): List<PlaceDto>
}