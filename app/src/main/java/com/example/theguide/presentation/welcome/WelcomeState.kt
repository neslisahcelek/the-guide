package com.example.theguide.presentation.welcome

import com.example.theguide.R
import com.example.theguide.domain.model.Place

data class WelcomeState (
    var placeList: List<Place> = listOf(),
    var currentPlace: Place = Place(
        id = 1,
        name = "Walkers",
        rating = 4.5,
        imageUrl = R.drawable.walkers.toString(),
    ),
    var currentPlaceIndex: Int = 0,
    var isListCompleted: Boolean = false,
    var userName: String = "",
    var userId: String = ""
)