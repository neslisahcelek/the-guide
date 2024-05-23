package com.example.theguide.data.remote.response

import com.squareup.moshi.Json

data class WishListResponse (
    @Json(name = "favorites")
    val wishList: List<Int>,
)