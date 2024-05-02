package com.example.theguide.presentation.login

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.util.Util
import com.stevdzasan.onetap.OneTapGoogleButton
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun LoginScreen(
    navigate: (String) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { PrimaryTopAppBar(title = stringResource(id = R.string.dashboard_screen_title)) }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                val oneTapSignInState = rememberOneTapSignInState()
                val isAuthenticated = remember { mutableStateOf(false) }

                OneTapSignInWithGoogle(
                    state = oneTapSignInState,
                    clientId = Util.WEB_CLIENT_ID,
                    onTokenIdReceived = { tokenId ->
                        isAuthenticated.value = true
                        Log.d("LOG", getUserFromTokenId(tokenId).toString())
                    },
                    onDialogDismissed = { message ->
                        Log.d("LOG", message)
                    }
                )

                OneTapGoogleButton(
                    clientId = Util.WEB_CLIENT_ID,
                    onClick = { oneTapSignInState.open() },
                    onUserReceived = { navigate.invoke(Route.WelcomeScreen.route) }
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    TheGuideTheme {
        LoginScreen()
    }
}