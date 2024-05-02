package com.example.theguide.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.presentation.topplaces.TopPlacesAction
import com.example.theguide.presentation.topplaces.TopPlacesState
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme

@Composable
fun CategoryRow(
    action: (TopPlacesAction) -> Unit = {},
    state: TopPlacesState
) {
    LazyRow(
        modifier = Modifier.fillMaxSize().padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.topPlaces) { place ->
            RecommendationCard(
                modifier = Modifier.width(250.dp),
                image = place.image,
                name = place.name,
                rating = place.rating.toString(),
                onClick = { action(TopPlacesAction.NavigateToPlaceDetails(place.id)) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryRowPreview() {
    TheGuideTheme {
        CategoryRow(
            state = TopPlacesState(
                category = "Kafe",
                topPlaces = listOf(
                    com.example.theguide.domain.model.Place(
                        id = "1",
                        name = "Walkers",
                        rating = 4.5,
                        image = R.drawable.walkers,
                    ),
                    com.example.theguide.domain.model.Place(
                        id = "1",
                        name = "Understone Coffee",
                        rating = 4.3,
                        image = R.drawable.understone,
                    )
                )
            )
        )
    }
}