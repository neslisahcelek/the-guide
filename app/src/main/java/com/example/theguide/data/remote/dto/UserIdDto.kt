package com.example.theguide.data.remote.dto

import com.squareup.moshi.Json

data class UserIdDto(
    @Json(name = "user_id") val userId: String,
    @Json(name = "error") val error: String,
    @Json(name = "message") val message: String
)