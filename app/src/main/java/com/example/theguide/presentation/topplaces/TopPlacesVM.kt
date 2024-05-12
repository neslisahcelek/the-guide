package com.example.theguide.presentation.topplaces

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.theguide.R
import com.example.theguide.domain.model.Place
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopPlacesVM @Inject constructor() : ViewModel() {
    var state by mutableStateOf(TopPlacesState())
        private set

    init {
        loadTopPlaces()
    }

    fun onAction(action: TopPlacesAction) {
        when (action) {
            is TopPlacesAction.LoadTopPlaces -> loadTopPlaces()
            is TopPlacesAction.NavigateToPlaceDetails -> navigateToPlaceDetails(action.placeId)
        }
    }

    private fun navigateToPlaceDetails(placeId: String) {

    }

    private fun loadTopPlaces() {
        state = state.copy(
            category = "Kafe",
            topPlaces = listOf(
                Place(
                    id = 1,
                    name = "Walkers",
                    rating = 4.5,
                    imageUrl = R.drawable.walkers.toString(),
                ),
                Place(
                    id = 2,
                    name = "Understone",
                    rating = 4.5,
                    imageUrl = R.drawable.understone.toString(),
                ),
                Place(
                    id = 3,
                    name = "Restaurant",
                    rating = 4.5,
                    imageUrl = R.drawable.bg.toString(),
                )
            )
        )
    }


}