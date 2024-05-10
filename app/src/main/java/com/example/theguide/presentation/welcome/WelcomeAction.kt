package com.example.theguide.presentation.welcome

sealed class WelcomeAction {
    data object SaveAppEntry: WelcomeAction()
    data class RatePlace(val placeId: String, val rating: Double): WelcomeAction()
}