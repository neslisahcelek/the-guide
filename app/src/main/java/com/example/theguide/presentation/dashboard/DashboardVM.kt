package com.example.theguide.presentation.dashboard

import Recommendation
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.data.local.UserDao
import com.example.theguide.domain.usecase.place.GetRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val getRecommendationUseCase: GetRecommendationUseCase,
    private val userDao: UserDao,
    private var savedStateHandle: SavedStateHandle
    ) : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onAction(action: DashboardAction) {
        when (action) {
            is DashboardAction.LoadDashboard -> {
                if (savedStateHandle.get<Boolean>("isInitialized") != true) {
                    getUserInfo()
                }
            }
        }
    }

    private fun getUserInfo() {
        savedStateHandle["isInitialized"] = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userEntity = userDao.getUser()
                Log.d("WelcomeVM", "GetUserInfo: ${userEntity?.firstName}")

                if (userEntity != null) {
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                userId = userEntity.googleTokenId,
                            )
                        }
                    }
                    //getRecommendations()
                    loadDashboard()
                }
            } catch (e: Exception) {
                Log.e("WelcomeVM", "Error fetching user: ${e.message}")
            }
        }
    }

    private fun getRecommendations() {
        if(savedStateHandle.get<Boolean>("isInitialized") == true){
            savedStateHandle["isInitialized"] = false
            viewModelScope.launch {
                _state.update {
                    it.copy(isLoading = true)
                }

                val result = getRecommendationUseCase.execute(
                    userId = state.value.userId
                )
                Log.d("getRecommendation", "${result.data?.size} ${result.message}")
                if (result.data != null) {
                    _state.update {
                        it.copy(
                            places = result.data
                        )
                    }
                    Log.d("getRecommendation", state.value.places[0].mapsUrl)
                }
            }
        }
    }

    private fun loadDashboard() {
        val placeList = listOf(
            Recommendation(
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
                placeName = "Restaurant",
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