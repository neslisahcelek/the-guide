package com.example.theguide.presentation.dashboard

import Recommendation
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme

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
                PrimaryTopAppBar(
                    title = stringResource(id = R.string.dashboard_screen_title),
                    onBackClick = { navigate.invoke(Route.WelcomeScreen.route) }
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
                            onAddToWishList = { placeId ->
                                action.invoke(
                                    DashboardAction.AddToWishList(
                                        userId = user?.id,
                                        placeId = placeId
                                    )
                                )
                            },
                            onRemoveFromWishList = { placeId ->
                                action.invoke(
                                    DashboardAction.RemoveFromWishList(
                                        userId = user?.id,
                                        placeId = placeId
                                    )
                                )
                            }
                        )
                    }
                }
                Button(
                    onClick = { navigate.invoke(Route.ProfileScreen.route) },
                ) {
                    Text(stringResource(id = R.string.top_places_screen_title))
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
                places = listOf(
                    Recommendation(
                        id = 1,
                        placeName = "Walkers",
                        address = "Kültür",
                        mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
                        rating = 4.5,
                        photos = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR_abBtnzBFl_-kLkB-fbC-nskMexTTiE7w9GroVJTGA&s"),
                        openingHours = listOf(
                            "Monday: 9:00 AM – 5:00 PM",
                            "Tuesday: 9:00 AM – 5:00 PM",
                            "Wednesday: 9:00 AM – 5:00 PM",
                            "Thursday: 9:00 AM – 5:00 PM",
                            "Friday: 9:00 AM – 5:00 PM",
                            "Saturday: 9:00 AM – 5:00 PM",
                            "Sunday: 9:00 AM – 5:00 PM"
                        ),
                        types = listOf("Cafe"),
                        reviews = listOf()
                    ),
                    Recommendation(
                        id = 2,
                        placeName = "Restaurant",
                        address = "Kültür",
                        mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
                        rating = 3.2,
                        photos = listOf("https://lh3.googleusercontent.com/places/ANXAkqF4Zu9H-23naAAe8lm4du88xkuNIhp-uBF-MSWb03-bKYz6uXR0_NDiDZnkgSIJ_Uxl2ctJ85TACMuLVWVTzMnaeCws6DamgM4=s1600-w400"),
                        openingHours = listOf(
                            "Monday: 9:00 AM – 5:00 PM",
                            "Tuesday: 9:00 AM – 5:00 PM",
                            "Wednesday: 9:00 AM – 5:00 PM",
                            "Thursday: 9:00 AM – 5:00 PM",
                            "Friday: 9:00 AM – 5:00 PM",
                            "Saturday: 9:00 AM – 5:00 PM",
                            "Sunday: 9:00 AM – 5:00 PM"
                        ),
                        types = listOf("Cafe"),
                        reviews = listOf()
                    )
                )
            )
        )
    }
}