package com.example.theguide.presentation.dashboard

sealed class DashboardAction {
    data object LoadDashboard : DashboardAction()
    data class NavigateToPlaceDetails(val placeId: String) : DashboardAction()
}