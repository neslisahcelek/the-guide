package com.example.theguide.domain.model

import com.example.theguide.data.remote.dto.PlaceDetails

data class Place(
    val id: Int,
    val name: String,
    val details: PlaceDetails? = null,
    val url: String? = null,
    val rating: Double? = null,
    val imageUrl: String,
)
