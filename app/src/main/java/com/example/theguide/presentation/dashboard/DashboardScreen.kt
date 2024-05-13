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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.domain.model.Place
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme

@Composable
fun DashboardScreen(
    action: (DashboardAction) -> Unit = {},
    state: DashboardState = DashboardState(),
    navigate: (String) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { PrimaryTopAppBar(
                title = stringResource(id = R.string.dashboard_screen_title),
                onBackClick = { navigate.invoke(Route.WelcomeScreen.route) }
            ) }
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
                            image = place.imageUrl,
                            name = place.name,
                            rating = place.rating.toString(),
                            onClick = {  }
                        )
                    }
                }
                Button(
                    onClick = { navigate.invoke(Route.TopPlacesScreen.route) },
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
                    Place(
                        id = 1,
                        name = "Walkers",
                        rating = 4.5,
                        imageUrl = R.drawable.walkers.toString(),
                    ),
                    Place(
                        id = 3,
                        name = "Restaurant",
                        rating = 4.5,
                        imageUrl = R.drawable.bg.toString(),
                    ),
                    Place(
                        id = 2,
                        name = "Understone",
                        rating = 4.5,
                        imageUrl = R.drawable.understone.toString(),
                    )
                )
            )
        )
    }
}