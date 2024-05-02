package com.example.theguide.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.domain.usecase.appentry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeVM @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel() {
    fun onAction(action: WelcomeAction) {
        when (action) {
            is WelcomeAction.SaveAppEntry -> saveAppEntry()
            WelcomeAction.NavigateToDashboard -> navigateToDashboard()
        }
    }

    private fun navigateToDashboard() {

    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntryUseCase()
        }
    }
}