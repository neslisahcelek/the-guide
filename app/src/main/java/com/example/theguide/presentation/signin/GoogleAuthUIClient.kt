package com.example.theguide.presentation.signin

import android.content.Intent
import android.content.IntentSender
import com.example.theguide.domain.model.User
import com.example.theguide.util.Util
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUIClient(
    private val oneTapClient: SignInClient,
) {
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredential).await().user
            val userAdditionalInfo =
                auth.signInWithCredential(googleCredential).await().additionalUserInfo
            val profile = userAdditionalInfo?.profile

            val firstName = profile?.get("given_name") as? String ?: ""

            SignInResult(
                data = user?.run {
                    User(
                        id = user.uid,
                        email = user.email ?: "",
                        displayName = firstName,
                        phoneNumber = user.phoneNumber ?: "",
                        picture = user.photoUrl?.toString() ?: ""
                    )
                },
                errorMessage = ""
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
            SignInResult(null, e.message ?: "Unknown sign in error")
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) {
                throw e
            }
        }
    }

    fun getSignedInUser(): User? = auth.currentUser?.run {
        User(
            id = uid,
            email = email ?: "",
            displayName = displayName?.split(" ")
                ?.joinToString(" ") { it.replaceFirstChar(Char::titlecaseChar) } ?: "",
            phoneNumber = phoneNumber ?: "",
            picture = photoUrl?.toString() ?: ""
        )
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(Util.WEB_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}