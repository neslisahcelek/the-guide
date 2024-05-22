package com.example.theguide.presentation.login

import com.example.theguide.domain.model.User

data class SignInResult (
    val data: User?,
    val errorMessage: String
)