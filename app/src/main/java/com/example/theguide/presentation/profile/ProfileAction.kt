package com.example.theguide.presentation.profile

sealed class ProfileAction {
    data object Logout: ProfileAction()
}