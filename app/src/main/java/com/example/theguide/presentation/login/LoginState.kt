package com.example.theguide.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val tokenId: String = "",
)