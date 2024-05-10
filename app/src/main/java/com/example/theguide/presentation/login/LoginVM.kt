package com.example.theguide.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.resource.Resource
import com.example.theguide.domain.usecase.place.CreateUserUseCase
import com.example.theguide.domain.usecase.place.GetUserUseCase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stevdzasan.onetap.GoogleUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    private val userCollection = Firebase.firestore.collection("users")

    var state by mutableStateOf(LoginState())
        private set


    private val _userId = MutableLiveData<Resource<String>>()
    val userId: LiveData<Resource<String>>
        get() = _userId

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.SaveAppEntry -> saveAppEntry()
            is LoginAction.CreateUser -> createUser(action.user)
            is LoginAction.TokenIdReceived -> saveTokenId(action.tokenId)
        }
    }

    private fun saveTokenId(tokenId: String) {
        state.copy(
            tokenId = tokenId
        )
    }

    private fun createUser(user: GoogleUser?) = CoroutineScope(Dispatchers.IO).launch {
        try {
            Log.d("LoginVM", "createUser: ${user?.givenName}")
            userCollection.add(com.example.theguide.domain.model.User(
                id = "",
                firstName = user?.givenName ?: "",
                lastName = user?.familyName ?: "",
                email = user?.email ?: "",
                locale = user?.locale,
                picture = user?.picture
            )).await()
        } catch (e: Exception) {
            Log.e("LoginVM", "createUser: ${e.message}")
        }

        /*
        val userInfo = UserInfo(userName = user?.fullName ?: "")

        viewModelScope.launch {
            val result = createUserUseCase.execute(userInfo)
            _userId.postValue(result)
            Log.d("LoginVM createUser", "userID: ${result.data} message: ${result.message}")
        }

         */
    }

    private fun saveUserInfo(user: GoogleUser?) {
        val info = UserInfo("dşdlşd")
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUserUseCase.execute(info)
            _userId.postValue(result)
            Log.d("LoginVM", "data: ${result.data} message: ${result.message}")
        }
    }

    private fun saveAppEntry() {
        //TODO("Not yet implemented")
    }
}