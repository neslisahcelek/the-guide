package com.example.theguide.presentation.topplaces

import com.example.theguide.domain.model.Place

data class TopPlacesState(
    val category: String = "",
    val topPlaces: List<Place> = emptyList(),
)

