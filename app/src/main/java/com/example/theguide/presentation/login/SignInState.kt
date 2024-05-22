package com.example.theguide.presentation.login

data class SignInState(
    val isLoading: Boolean = false,
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)