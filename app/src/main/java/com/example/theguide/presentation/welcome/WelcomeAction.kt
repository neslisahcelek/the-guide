package com.example.theguide.presentation.welcome

sealed class WelcomeAction {
    data object SaveAppEntry: WelcomeAction()
    data object NavigateToDashboard: WelcomeAction()
    data class RatePlace(val placeId: String, val rating: Double): WelcomeAction()
}