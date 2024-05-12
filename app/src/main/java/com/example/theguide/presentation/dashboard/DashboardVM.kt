package com.example.theguide.presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theguide.R
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.repository.PlaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    //private val repository: PlaceRepository
) : ViewModel() {
    var state by mutableStateOf(DashboardState())
        private set

    init {
        onAction(DashboardAction.LoadDashboard)
    }

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.LoadDashboard -> loadDashboard()
            is DashboardAction.NavigateToPlaceDetails -> {}
        }
    }


    private fun loadDashboard() {
        state = state.copy(
            places = listOf(
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

    /*
    private fun loadDashboard() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
        }


    }

     */
}