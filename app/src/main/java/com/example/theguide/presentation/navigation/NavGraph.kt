package com.example.theguide.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.theguide.presentation.dashboard.DashboardScreen
import com.example.theguide.presentation.dashboard.DashboardVM
import com.example.theguide.presentation.login.LoginScreen
import com.example.theguide.presentation.login.LoginVM
import com.example.theguide.presentation.topplaces.TopPlacesScreen
import com.example.theguide.presentation.topplaces.TopPlacesVM
import com.example.theguide.presentation.welcome.WelcomeScreen
import com.example.theguide.presentation.welcome.WelcomeVM

@Composable
fun TheGuideNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.LoginScreen.route
    ) {
        composable(Route.LoginScreen.route) {
            val viewModel: LoginVM = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LoginScreen(
                action = viewModel::onAction,
                navigate = { route ->
                    navController.navigate(route)
                },
                state = state
            )
        }

        composable(Route.WelcomeScreen.route) {
            val viewModel: WelcomeVM = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            WelcomeScreen(
                action = viewModel::onAction,
                navigate = { route ->
                    navController.navigate(route)
                },
                state = state
            )
        }

        composable(Route.DashboardScreen.route) {
            val viewModel: DashboardVM = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            DashboardScreen(
                action = viewModel::onAction,
                state = state,
                navigate = { route ->
                    navController.navigate(route)
                })
        }

        composable(Route.TopPlacesScreen.route) {
            val viewModel: TopPlacesVM = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            TopPlacesScreen(
                action = viewModel::onAction,
                state = state,
                navigate = { route ->
                    navController.navigate(route)
                })
        }

    }
}