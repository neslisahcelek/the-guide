package com.example.theguide.data.remote.response

import com.squareup.moshi.Json

data class CreateUserResponse(
    @Json(name = "error") val error: String,
    @Json(name = "message") val message: String
)