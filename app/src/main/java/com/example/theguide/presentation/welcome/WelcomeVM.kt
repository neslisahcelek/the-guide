package com.example.theguide.presentation.welcome

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.R
import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.model.Place
import com.example.theguide.domain.resource.Resource
import com.example.theguide.domain.usecase.appentry.AppEntryUseCases
import com.example.theguide.domain.usecase.place.GetUserUseCase
import com.example.theguide.presentation.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(WelcomeState())
    val state = _state.asStateFlow()

    private val _userId = MutableLiveData<Resource<String>>()
    val userId: LiveData<Resource<String>>
        get() = _userId

    init {
        val placeList = listOf(
            Place(
                id = "2",
                name = "Understone",
                rating = 4.5,
                image = R.drawable.understone,
            ),
            Place(
                id = "1",
                name = "Walkers",
                rating = 4.5,
                image = R.drawable.walkers,
            ),
            Place(
                id = "3",
                name = "Restaurant",
                rating = 4.5,
                image = R.drawable.bg,
            )
        )

        _state.update {
            it.copy(
                placeList = placeList,
                currentPlace = placeList.first(),
                currentPlaceIndex = 0
            )
        }
    }

    fun onAction(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.SaveAppEntry -> saveAppEntry()
            is WelcomeAction.RatePlace -> addRating(action.placeId, action.rating)
        }
    }

    private fun addRating(placeId: String, rating: Double) {
        //api
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
                it.copy(
                    currentPlace = state.value.placeList[state.value.currentPlaceIndex++]
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