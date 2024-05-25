package com.example.theguide.presentation.topplaces

import com.example.theguide.domain.model.PlaceModel

data class TopPlacesState(
    val category: String = "",
    val topPlaces: List<PlaceModel> = emptyList(),
)

