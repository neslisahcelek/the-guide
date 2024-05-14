package com.example.theguide.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.theguide.data.local.UserDao
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
class ProfileVM @Inject constructor(
    private val userDao: UserDao
) : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state = _state.asStateFlow()

    init {
        getUserInfo()
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.Logout -> logout()
        }
    }

    private fun logout() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.deleteUser(state.value.user!!)
            } catch (e: Exception) {
                Log.e("ProfileVM", "Error logging out: ${e.message}")
            }
        }
    }

    private fun getUserInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userEntity = userDao.getUser()
                Log.d("ProfileVM", "getUserInfo: ${userEntity?.firstName}")

                if (userEntity != null) {
                    withContext(Dispatchers.Main) {
                        _state.update {
                            it.copy(
                                user = userEntity,
                                profileImage = userEntity.imageUrl,
                                userName = userEntity.firstName + " " + userEntity.lastName,
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("WelcomeVM", "Error fetching user: ${e.message}")
            }
        }
    }
}