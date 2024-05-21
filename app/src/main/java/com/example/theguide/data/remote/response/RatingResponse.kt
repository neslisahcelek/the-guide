package com.example.theguide.data.remote.response

import com.squareup.moshi.Json

data class RatingResponse (
    @Json(name = "message") val message: String,
)