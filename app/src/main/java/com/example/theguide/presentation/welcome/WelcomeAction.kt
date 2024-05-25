package com.example.theguide.presentation.welcome

sealed class WelcomeAction {
    data class RatePlace(val userId: String, val placeId: Int, val rating: Double): WelcomeAction()
}