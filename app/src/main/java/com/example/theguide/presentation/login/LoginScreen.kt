package com.example.theguide.presentation.login

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theguide.R
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.theme.AlegreyaFontFamily
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography
import com.example.theguide.ui.theme.bg
import com.example.theguide.ui.theme.softOrange
import com.example.theguide.util.Util
import com.stevdzasan.onetap.OneTapGoogleButton
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState

@Composable
fun LoginScreen(
    action: (LoginAction) -> Unit = {},
    navigate: (String) -> Unit = {},
    state: LoginState
) {
    LaunchedEffect(key1 = Unit, block = { action.invoke(LoginAction.CheckLoginStatus) })

    if (state.isLoggedIn) {
        navigate.invoke(Route.WelcomeScreen.route)
    } else {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .gradientBackground(
                        listOf(
                            Color.White,
                            softOrange,
                            bg
                        )
                    )
            ) {
                /*
                Image(
                    painterResource(id = R.drawable.bg),
                    contentDescription = "background",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                )
                 */
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(150.dp))

                    Icon(
                        Icons.Filled.CheckCircle,
                        contentDescription = "app icon",
                        tint = softOrange,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(340.dp))

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp),
                        textAlign = TextAlign.Start,
                        text = stringResource(id = R.string.login_screen_title),
                        style = Typography.headlineMedium.copy(
                            fontSize = 35.sp,
                            fontWeight = FontWeight(350),
                            fontFamily = AlegreyaFontFamily
                        ),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    val oneTapSignInState = rememberOneTapSignInState()

                    OneTapSignInWithGoogle(
                        state = oneTapSignInState,
                        rememberAccount = true,
                        clientId = Util.WEB_CLIENT_ID,
                        onTokenIdReceived = { tokenId ->
                            Log.d("LOG", "tokenid received")
                            action.invoke(LoginAction.TokenIdReceived(tokenId))
                            action.invoke(LoginAction.CreateUser(getUserFromTokenId(tokenId)))
                        },
                        onDialogDismissed = {}
                    )

                    OneTapGoogleButton(
                        clientId = Util.WEB_CLIENT_ID,
                        onClick = { oneTapSignInState.open() },
                        onUserReceived = {
                            Log.d("LOG", "user received")
                        },
                        border = BorderStroke(
                            width = 2.dp,
                            color = softOrange,
                        )
                    )

                }

            }
        }
    }
}

@Composable
fun Modifier.gradientBackground(colors: List<Color>): Modifier = this
    .background(
        brush = Brush.verticalGradient(
            colors = colors
        )
    )

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TheGuideTheme {
        LoginScreen(state = LoginState())
    }
}