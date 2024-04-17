package com.example.theguide.presentation.navigation

sealed class Route(
    val route: String
) {
    data object DashboardScreen : Route("dashboardScreen")
    data object WelcomeScreen : Route("welcomeScreen")
    data object ProfileScreen : Route("profileScreen")
    data object TopPlacesScreen : Route("topPlacesScreen")
}
