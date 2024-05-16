package com.example.theguide.data.remote.dto

import com.squareup.moshi.Json

data class UserInfo (
    @field:Json(name = "reviewer_name")
    val userName: String,
)
