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
import com.example.theguide.domain.usecase.appentry.AppEntryUseCases
import com.example.theguide.domain.usecase.place.GetRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val getRecommendationUseCase: GetRecommendationUseCase,
    ) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    init {
        loadDashboard()
    }

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.LoadDashboard -> loadDashboard()
            is DashboardAction.NavigateToPlaceDetails -> {}
        }
    }


    private fun loadDashboard() {
        val placeList = listOf(
            Place(
                id = 2,
                name = "Understone",
                rating = 4.5,
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR_abBtnzBFl_-kLkB-fbC-nskMexTTiE7w9GroVJTGA&s",
            ),
            Place(
                id = 1,
                name = "Walkers",
                rating = 4.5,
                imageUrl = "https://lh3.googleusercontent.com/p/AF1QipP5WCtkTdnMTOPErh_wT_2mvaoGvxGvkqMajlvl=s1360-w1360-h1020-rw",
            ),
            Place(
                id = 3,
                name = "Restaurant",
                rating = 4.5,
                imageUrl = "https://lh3.googleusercontent.com/places/ANXAkqF4Zu9H-23naAAe8lm4du88xkuNIhp-uBF-MSWb03-bKYz6uXR0_NDiDZnkgSIJ_Uxl2ctJ85TACMuLVWVTzMnaeCws6DamgM4=s1600-w400",
            )
        )
        _state.update {
            it.copy(
                places = placeList
            )
        }
        /*
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }

            getRecommendationUseCase.execute(userId = 1)
        }

         */
    }
}