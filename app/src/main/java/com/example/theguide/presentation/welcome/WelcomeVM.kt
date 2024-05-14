package com.example.theguide.presentation.welcome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.R
import com.example.theguide.data.local.UserDao
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.resource.Resource
import com.example.theguide.domain.usecase.appentry.AppEntryUseCases
import com.example.theguide.domain.usecase.place.AddRatingUseCase
import com.example.theguide.domain.usecase.place.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val getUserUseCase: GetUserUseCase,
    private val addRatingUseCase: AddRatingUseCase,
    private val userDao: UserDao
) : ViewModel() {
    private val _state = MutableStateFlow(WelcomeState())
    val state = _state.asStateFlow()

    private val _userId = MutableLiveData<Resource<String>>()
    val userId: LiveData<Resource<String>>
        get() = _userId

    init {
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
                placeList = placeList,
                currentPlace = placeList.first(),
                currentPlaceIndex = 0
            )
        }

        getUserInfo()
    }

    fun onAction(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.SaveAppEntry -> saveAppEntry()
            is WelcomeAction.RatePlace -> addRating(action.placeId, action.rating)
            WelcomeAction.getUserInfo -> getUserInfo()
        }
    }

    private fun getUserInfo() {
        CoroutineScope(IO).launch {
            try {
                val userEntity = userDao.getUser()
                Log.d("WelcomeVM", "getUserInfo: ${userEntity?.firstName}")

                withContext(Main) {
                    _state.update {
                        it.copy(
                            userName = userEntity?.firstName ?: "",
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("WelcomeVM", "Error fetching user: ${e.message}")
            }
        }
    }


    private fun addRating(placeId: Int, rating: Double) {
        viewModelScope.launch {
            addRatingUseCase.execute(
                userId = userId.value?.data?.toInt() ?: 0,
                placeId = placeId,
                rating = rating
            )
        }
        if (!state.value.isListCompleted) {
            getNextPlace()
        }
    }

    private fun getNextPlace() {
        _state.update {
            if (state.value.currentPlace == state.value.placeList.last()) {
                it.copy(
                    isListCompleted = true
                )
            } else {
                val nextIndex = state.value.currentPlaceIndex+1
                it.copy(
                    currentPlaceIndex = nextIndex,
                    currentPlace = state.value.placeList[nextIndex]
                )
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntryUseCase()
        }
    }
}