package com.example.theguide.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.ui.component.RecommendationCard

@Composable
fun CategoryRow() {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            RecommendationCard(
                image = R.drawable.walkers,
                name = "Walkers",
                rating = "4.5",
                onClick = { }
            )
        }
        item {
            RecommendationCard(
                image = R.drawable.understone,
                name = "Understone Coffee",
                rating = "4.5",
                onClick = { }
            )
        }
    }
}