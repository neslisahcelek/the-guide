package com.example.theguide.presentation.login

import com.stevdzasan.onetap.GoogleUser


sealed class LoginAction {
    data object SaveAppEntry : LoginAction()
    data object CheckLoginStatus : LoginAction()
    data class CreateUser(val user: GoogleUser?) : LoginAction()
    data class TokenIdReceived(val tokenId: String) : LoginAction()
}
