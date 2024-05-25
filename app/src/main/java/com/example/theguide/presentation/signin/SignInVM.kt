package com.example.theguide.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theguide.domain.usecase.place.CreateUserUseCase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInVM @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
) : ViewModel() {
    private val userCollection = Firebase.firestore.collection("users")

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(signInResult: SignInResult) {
        val userData = signInResult.data
        val signInError = signInResult.errorMessage

        if (userData != null) {
            Log.d("SignInVM", "Userdata not null: ${userData.id}")
            saveUserToDb(userData.id)
        }

        _state.update { currentState ->
            currentState.copy(
                signInError = signInError,
                isSignInSuccessful = userData != null
            )
        }
    }

    fun resetState() {
        _state.update {
            SignInState()
        }
    }

    private fun saveUserToDb(tokenId: String) {
        viewModelScope.launch {
            val result = createUserUseCase.execute(tokenId)
            Log.d("SignInVM saveUser", "userID: ${result.data} message: ${result.message}")
        }
    }

    /*
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
                       email = user.email ?: "",
                       picture = user.picture ?: ""
                   ),
                   SetOptions.merge()
               )
               .addOnSuccessListener { userReference ->
                   saveUsertoDb()
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
                Log.e("SignInVM", "Error saving user to Room: ${e.message}", e)
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
            Log.d("SignInVM", "checkLoginStatus: ${state.value.isLoggedIn}")

            if (userEntity != null) {
                Log.d("SignInVM", "user: ${userEntity.firstName}")

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

     */
}