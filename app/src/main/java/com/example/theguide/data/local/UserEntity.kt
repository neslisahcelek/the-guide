package com.example.theguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey
    val id: String,
    val googleTokenId: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
