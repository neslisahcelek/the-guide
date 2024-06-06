package com.example.theguide.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.theguide.R
import com.example.theguide.presentation.navigation.Route

@Composable
fun TheGuideTopBar(navController: NavController, actions: TopBarActions) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val route = currentRoute?.destination?.route

    when (route) {
        Route.DashboardScreen.route -> {
            DashboardTopAppBar(
                title = stringResource(id = R.string.dashboard_screen_title),
                onFilterClick = actions.onFilterClick,
                onProfileClick = actions.onProfileClick
            )
        }
        Route.WishListScreen.route -> {
            PrimaryTopAppBar(
                title = stringResource(id = R.string.wishlist_screen_title)
            )
        }
        Route.VisitedListScreen.route -> {
            PrimaryTopAppBar(
                title = stringResource(id = R.string.visited_list_screen_title)
            )
        }
        Route.ProfileScreen.route -> {
            ProfileTopAppBar(
                title = stringResource(id = R.string.profile_screen_title),
                onBackClick = { navController.navigate(Route.DashboardScreen.route) }
            )
        }
        else -> {}
    }
}

data class TopBarActions(
    val onFilterClick: () -> Unit,
    val onProfileClick: () -> Unit
)