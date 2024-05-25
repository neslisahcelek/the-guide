package com.example.theguide.presentation.welcome.views

import Recommendation
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
import com.example.theguide.presentation.topplaces.TopPlacesAction
import com.example.theguide.presentation.topplaces.TopPlacesState
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.util.Util

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
                place = place,
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
                topPlaces = Util.getPlaceList()
            )
        )
    }
}