package com.example.theguide.data.remote

import com.example.theguide.data.remote.dto.PlaceDto
import com.example.theguide.data.remote.dto.UserIdDto
import com.example.theguide.data.remote.dto.id
import com.example.theguide.data.remote.dto.place
import org.json.JSONArray
import org.json.JSONObject
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
    suspend fun fetchData2(): String

    @GET("/place_name/1")
    suspend fun fetchData3(): place

    @GET("/recommend?uid=1&k=1&remove_seen=false")
    suspend fun fetchData4(): List<Double>

    @POST("/add_new_user")
    suspend fun fetchData(@Body userData: UserInfo): UserIdDto
}
