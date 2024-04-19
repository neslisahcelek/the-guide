package com.example.theguide.domain.model

data class Place(
    val id: String,
    val name: String,
    val description: String? = null,
    val rating: Double,
    val image: Int
)
