package com.example.theguide.presentation.topplaces

sealed class TopPlacesAction {
    data object LoadTopPlaces : TopPlacesAction()
    data class NavigateToPlaceDetails(val placeId: String) : TopPlacesAction()
}