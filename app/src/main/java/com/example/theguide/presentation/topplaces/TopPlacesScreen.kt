package com.example.theguide.presentation.topplaces

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.domain.model.Place
import com.example.theguide.ui.component.PrimaryAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme

@Composable
fun TopPlacesScreen(
    event: (TopPlacesEvent) -> Unit = {},
    navigate: (String) -> Unit = {},
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { PrimaryAppBar(title = stringResource(id = R.string.top_places_screen_title)) }
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
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    /*
                    item {
                        LazyRow(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            item {
                                RecommendationCard(
                                    image = R.drawable.walkers,
                                    name = "Walkers",
                                    rating = "4.5",
                                    onClick = { navigate.invoke("Walkers") }
                                )
                                RecommendationCard(
                                    image = R.drawable.understone,
                                    name = "Understone Coffee",
                                    rating = "4.5",
                                    onClick = { navigate.invoke("Understone") }
                                )
                            }
                        }
                    }

                     */
                    items(event.invoke(TopPlacesEvent.LoadTopPlaces)) { place ->
                        RecommendationCard(
                            image = place.image,
                            name = place.name,
                            rating = place.rating.toString(),
                            onClick = { navigate.invoke(place.name) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TopPlacesScreenPreview() {
    TheGuideTheme {
        TopPlacesScreen(

        )
    }
}