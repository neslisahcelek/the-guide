package com.example.theguide.presentation.welcome

sealed class WelcomeAction {
    data object SaveAppEntry: WelcomeAction()
    data class RatePlace(val placeId: Int, val rating: Double): WelcomeAction()
    data object getUserInfo: WelcomeAction()
}