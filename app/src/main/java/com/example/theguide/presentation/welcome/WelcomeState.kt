package com.example.theguide.presentation.welcome

import Recommendation
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.util.Util

data class WelcomeState (
    var placeList: List<PlaceModel> = listOf(),
    var currentPlace: PlaceModel =  Util.getPlace(),
    var currentPlaceIndex: Int = 0,
    var isListCompleted: Boolean = false,
    var userName: String = "",
    var userId: String = "",
    var isLoading: Boolean = false
)