package com.example.theguide.presentation.profile

import com.example.theguide.data.local.UserEntity

data class ProfileState (
    var profileImage: String? = null,
    var userName: String = "",
    var city: String = "Antalya",
    var user: UserEntity? = null
)