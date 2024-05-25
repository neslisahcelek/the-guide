package com.example.theguide.presentation.topplaces

import Recommendation
import androidx.lifecycle.ViewModel
import com.example.theguide.util.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TopPlacesVM @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(TopPlacesState())
    val state = _state.asStateFlow()

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
        val placeList = Util.getPlaceList()
        _state.update {
            it.copy(
                category = "Kafe",
                topPlaces = placeList
            )
        }
    }


}