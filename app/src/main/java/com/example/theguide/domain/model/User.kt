package com.example.theguide.domain.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val locale: String?,
    val picture: String?,
)
