package com.example.theguide.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.data.local.UserDao
import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.model.User
import com.example.theguide.domain.resource.Resource
import com.example.theguide.domain.usecase.place.CreateUserUseCase
import com.example.theguide.domain.usecase.place.GetUserUseCase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stevdzasan.onetap.GoogleUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val userDao: UserDao
) : ViewModel() {
    private val userCollection = Firebase.firestore.collection("users")

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _userId = MutableLiveData<Resource<String>>()
    val userId: LiveData<Resource<String>>
        get() = _userId

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.SaveAppEntry -> saveAppEntry()
            is LoginAction.CreateUser -> createUser(action.user)
            is LoginAction.TokenIdReceived -> saveTokenId(action.tokenId)
            is LoginAction.CheckLoginStatus -> checkLoginStatus()
        }
    }

    private fun saveTokenId(tokenId: String) {
        _state.update {
            it.copy(tokenId = tokenId)
        }
    }

    private fun createUser(user: GoogleUser?) = CoroutineScope(IO).launch {
        if (user != null) {
            userCollection.document(user.email!!)
                .set(
                    User(
                        id = "",
                        firstName = user.givenName ?: "",
                        lastName = user.familyName ?: "",
                        email = user.email ?: "",
                        locale = user.locale,
                        picture = user.picture ?: ""
                    ),
                    SetOptions.merge()
                )
                .addOnSuccessListener { userReference ->
                    saveUserToRoom(user)
                    Log.d("db success", "Document written with ID: $userReference")
                }.addOnFailureListener { e ->
                    Log.w("db error", "Error adding user", e)
                }
        }
    }

    private fun saveUserToRoom(user: GoogleUser?) {
        CoroutineScope(IO).launch {
            try {
                userDao.upsertUser(
                    com.example.theguide.data.local.UserEntity(
                        id = "",
                        googleTokenId = state.value.tokenId,
                        firstName = user?.givenName ?: "",
                        lastName = user?.familyName ?: "",
                        email = user?.email ?: "",
                        imageUrl = user?.picture ?: ""
                    )
                )
                updateLoginStatus()
            } catch (e: Exception) {
                Log.e("LoginVM", "Error saving user to Room: ${e.message}", e)
            }
        }
    }

    private fun updateLoginStatus() {
        _state.update {
            it.copy(
                isLoggedIn = true
            )
        }
    }

    private fun checkLoginStatus() {
        CoroutineScope(IO).launch {
            val userEntity = userDao.getUser()
            Log.d("LoginVM", "checkLoginStatus: ${state.value.isLoggedIn}")

            if (userEntity != null) {
                Log.d("LoginVM", "user: ${userEntity.firstName}")

                withContext(Main) {
                    _state.update {
                        it.copy(
                            isLoggedIn = true
                        )
                    }
                }
            }
        }
    }

    private fun saveUser(ref: DocumentReference, user: GoogleUser?) {
        val userInfo = UserInfo(userName = user?.fullName ?: "")

        viewModelScope.launch {
            val result = createUserUseCase.execute(userInfo)
            _userId.postValue(result)
            Log.d("LoginVM saveUser", "userID: ${result.data} message: ${result.message}")
        }
    }

    private fun saveUserInfo(user: GoogleUser?) {
        val info = UserInfo("dşdlşd")
        viewModelScope.launch(IO) {
            val result = getUserUseCase.execute(info)
            _userId.postValue(result)
            Log.d("LoginVM", "data: ${result.data} message: ${result.message}")
        }
    }

    private fun saveAppEntry() {
        //TODO("Not yet implemented")
    }
}