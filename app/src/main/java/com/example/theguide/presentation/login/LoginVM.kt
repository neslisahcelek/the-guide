package com.example.theguide.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.data.local.UserDao
import com.example.theguide.data.remote.UserInfo
import com.example.theguide.domain.resource.Resource
import com.example.theguide.domain.usecase.place.CreateUserUseCase
import com.example.theguide.domain.usecase.place.GetUserUseCase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stevdzasan.onetap.GoogleUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    init {
        isUserLoggedIn()
    }

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.SaveAppEntry -> saveAppEntry()
            is LoginAction.CreateUser -> createUser(action.user)
            is LoginAction.TokenIdReceived -> saveTokenId(action.tokenId)
        }
    }

    private fun saveTokenId(tokenId: String) {
        _state.update {
            it.copy(tokenId = tokenId)
        }
    }

    private fun createUser(user: GoogleUser?) = CoroutineScope(IO).launch {
        //userCollection.document(user, SetOptions.merge())

        Log.d("LoginVM", "createUser: ${user?.givenName}")
        userCollection.add(
            com.example.theguide.domain.model.User(
                id = "",
                firstName = user?.givenName ?: "",
                lastName = user?.familyName ?: "",
                email = user?.email ?: "",
                locale = user?.locale,
                picture = user?.picture
            )
        ).addOnSuccessListener { userReference ->
            saveUserToRoom(userReference, user)
            Log.d("db success", "DocumentSnapshot written with ID: ${userReference.id}")
        }.addOnFailureListener { e ->
            Log.w("db error", "Error adding user", e)
        }
    }

    private fun saveUserToRoom(ref: DocumentReference, user: GoogleUser?) {
        CoroutineScope(IO).launch {
            userDao.upsertUser(
                com.example.theguide.data.local.UserEntity(
                    id = ref.id,
                    googleTokenId = state.value.tokenId,
                    firstName = user?.givenName ?: "",
                    lastName = user?.familyName ?: "",
                    email = user?.email ?: ""
                )
            )
        }
    }

    private fun isUserLoggedIn() {
        _state.update {
            it.copy(isLoggedIn = true)
        }
        // TODO
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