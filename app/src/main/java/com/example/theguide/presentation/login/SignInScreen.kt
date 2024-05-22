package com.example.theguide.presentation.login

import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theguide.R
import com.example.theguide.ui.theme.AlegreyaFontFamily
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography
import com.example.theguide.ui.theme.bg1
import com.example.theguide.ui.theme.bg2
import com.example.theguide.ui.theme.softBlurOrange
import com.example.theguide.ui.theme.softOrange

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit = {}
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .gradientBackground(
                    listOf(
                        bg1,
                        bg2,
                        softOrange,
                        softBlurOrange
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

                Button(onClick = onSignInClick) {
                    Text(
                        text = stringResource(id = R.string.sign_in_with_google),
                        color = Color.White
                    )
                }
                /*
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

                 */

            }

        }
    }
}

fun Modifier.gradientBackground(colors: List<Color>): Modifier = this
    .background(
        brush = Brush.linearGradient(
            colors = colors
        )
    )

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    TheGuideTheme {
        SignInScreen(state = SignInState())
    }
}