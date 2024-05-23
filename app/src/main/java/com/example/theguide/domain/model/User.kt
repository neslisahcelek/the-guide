package com.example.theguide.domain.model

data class User(
    val id: String,
    val displayName: String,
    val phoneNumber: String,
    val email: String,
    val picture: String?,
)
