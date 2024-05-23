package com.example.theguide.presentation.dashboard

import Recommendation
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.domain.model.User
import com.example.theguide.domain.usecase.place.GetRecommendationUseCase
import com.example.theguide.domain.usecase.wishlist.WishListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val getRecommendationUseCase: GetRecommendationUseCase,
    private var savedStateHandle: SavedStateHandle,
    private val wishListUseCases: WishListUseCases
    ) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.LoadDashboard -> {
                loadDashboard()
                //getRecommendations(user = action.user)
            }
            is DashboardAction.AddToWishList -> addToWishList(action.userId, action.placeId)
            is DashboardAction.RemoveFromWishList -> removeFromWishList(action.userId, action.placeId)
        }
    }

    private fun removeFromWishList(userId: String?, placeId: Int) {
        viewModelScope.launch {
            val result = wishListUseCases.removeFromWishListUseCase.execute(
                userId = userId ?: "",
                placeId = placeId
            )
            if (result.data != null) {
                Log.d("removeFromWishList", "Success: ${result.data}")
            } else {
                Log.d("removeFromWishList", "Error $result.message ?:")
            }
        }
        _state.update {
            it.copy(
                wishList = it.wishList - placeId
            )
        }
        Log.d("removeFromWishList", "userId: $userId, placeId: $placeId list: ${_state.value.wishList}")
    }

    private fun addToWishList(userId: String?, placeId: Int) {
        viewModelScope.launch {
            val result = wishListUseCases.addToWishListUseCase.execute(
                userId = userId ?: "",
                placeId = placeId
            )
            if (result.data != null) {
                Log.d("addToWishListUseCase", "Success: ${result.data}")
            } else {
                Log.d("addToWishListUseCase", "Error $result.message ?:")
            }
        }
        _state.update {
            it.copy(
                wishList = it.wishList + placeId
            )
        }
        Log.d("addToWishList", "userId: $userId, placeId: $placeId list: ${_state.value.wishList}")
    }

    private fun getRecommendations(user: User?) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            val result = getRecommendationUseCase.execute(
                userId = user?.id ?: ""
            )
            Log.d("getRecommendation", "${result.data?.size} ${result.message}")
            if (result.data != null) {
                _state.update {
                    it.copy(
                        places = result.data
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        error = result.message
                    )
                }
                Log.d("getRecommendation", result.message ?: "")
            }
        }
    }

    private fun loadDashboard() {
        val placeList = listOf(
            Recommendation(
                id = 1,
                placeName = "Walkers",
                address = "Kültür",
                mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
                rating = 4.5,
                photos = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR_abBtnzBFl_-kLkB-fbC-nskMexTTiE7w9GroVJTGA&s"),
                openingHours = listOf("Monday: 9:00 AM – 5:00 PM", "Tuesday: 9:00 AM – 5:00 PM", "Wednesday: 9:00 AM – 5:00 PM", "Thursday: 9:00 AM – 5:00 PM", "Friday: 9:00 AM – 5:00 PM", "Saturday: 9:00 AM – 5:00 PM", "Sunday: 9:00 AM – 5:00 PM"),
                types = listOf("Cafe"),
                reviews = listOf()
            ),
            Recommendation(
                id = 2,
                placeName = "Köfteci Cihat",
                address = "Kültür",
                mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
                rating = 3.2,
                photos = listOf("https://lh3.googleusercontent.com/places/ANXAkqF4Zu9H-23naAAe8lm4du88xkuNIhp-uBF-MSWb03-bKYz6uXR0_NDiDZnkgSIJ_Uxl2ctJ85TACMuLVWVTzMnaeCws6DamgM4=s1600-w400",),
                openingHours = listOf("Monday: 9:00 AM – 5:00 PM", "Tuesday: 9:00 AM – 5:00 PM", "Wednesday: 9:00 AM – 5:00 PM", "Thursday: 9:00 AM – 5:00 PM", "Friday: 9:00 AM – 5:00 PM", "Saturday: 9:00 AM – 5:00 PM", "Sunday: 9:00 AM – 5:00 PM"),
                types = listOf("Cafe"),
                reviews = listOf()
            ),
            Recommendation(
                id = 3,
                placeName = "Köfteci Cihat",
                address = "Kültür",
                mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
                rating = 3.2,
                photos = listOf("https://lh3.googleusercontent.com/places/ANXAkqF4Zu9H-23naAAe8lm4du88xkuNIhp-uBF-MSWb03-bKYz6uXR0_NDiDZnkgSIJ_Uxl2ctJ85TACMuLVWVTzMnaeCws6DamgM4=s1600-w400",),
                openingHours = listOf("Monday: 9:00 AM – 5:00 PM", "Tuesday: 9:00 AM – 5:00 PM", "Wednesday: 9:00 AM – 5:00 PM", "Thursday: 9:00 AM – 5:00 PM", "Friday: 9:00 AM – 5:00 PM", "Saturday: 9:00 AM – 5:00 PM", "Sunday: 9:00 AM – 5:00 PM"),
                types = listOf("Cafe"),
                reviews = listOf()
            ),
            Recommendation(
                id = 4,
                placeName = "Köfteci Cihat",
                address = "Kültür",
                mapsUrl = "https://www.google.com/maps/search/?api=1&query=36.8465237%2C30.7597125&query_place_id=ChIJ91Ez--ibwxQRFdcL4FiFLNc",
                rating = 3.2,
                photos = listOf("https://lh3.googleusercontent.com/places/ANXAkqF4Zu9H-23naAAe8lm4du88xkuNIhp-uBF-MSWb03-bKYz6uXR0_NDiDZnkgSIJ_Uxl2ctJ85TACMuLVWVTzMnaeCws6DamgM4=s1600-w400",),
                openingHours = listOf("Monday: 9:00 AM – 5:00 PM", "Tuesday: 9:00 AM – 5:00 PM", "Wednesday: 9:00 AM – 5:00 PM", "Thursday: 9:00 AM – 5:00 PM", "Friday: 9:00 AM – 5:00 PM", "Saturday: 9:00 AM – 5:00 PM", "Sunday: 9:00 AM – 5:00 PM"),
                types = listOf("Cafe"),
                reviews = listOf()
            )
        )
        _state.update {
            it.copy(
                places = placeList
            )
        }
    }
}