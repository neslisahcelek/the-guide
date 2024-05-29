package com.example.theguide.data.remote.dto

import com.squareup.moshi.Json

data class RecommendInfo(
    @field:Json(name = "firebase_id")
    val userId: String,
    @field:Json(name = "k")
    val recommendationLimit: Int = 5,
    @field:Json(name = "remove_seen")
    val removeSeen: Boolean = true,
    @field:Json(name = "districts")
    val districtList: List<String> = emptyList(),
)