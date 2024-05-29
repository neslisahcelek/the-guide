package com.example.theguide.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.domain.model.User
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.DashboardTopAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.util.Util

@Composable
fun DashboardScreen(
    action: (DashboardAction) -> Unit = {},
    state: DashboardState = DashboardState(),
    navigate: (String) -> Unit = {},
    user: User? = null
) {
    LaunchedEffect(key1 = Unit) {
        action.invoke(DashboardAction.LoadDashboard(user))
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                DashboardTopAppBar(
                    title = stringResource(id = R.string.dashboard_screen_title),
                    onFilterClick = { action.invoke(DashboardAction.FilterDistricts(emptyList())) },
                    onProfileClick = { navigate.invoke(Route.ProfileScreen.route) }
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    items(state.places) { place ->
                        RecommendationCard(
                            place = place,
                            onAddToWishList = {
                                action.invoke(
                                    DashboardAction.AddToWishList(
                                        userId = user?.id,
                                        place = place
                                    )
                                )
                            },
                            onRemoveFromWishList = {
                                action.invoke(
                                    DashboardAction.RemoveFromWishList(
                                        userId = user?.id,
                                        place = place
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DashboardScreenPreview() {
    TheGuideTheme {
        DashboardScreen(
            state = DashboardState(
                places = Util.getPlaceList()
            )
        )
    }
}