package com.example.theguide.presentation.signin

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theguide.R
import com.example.theguide.ui.theme.AlegreyaFontFamily
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography
import com.example.theguide.ui.theme.bg
import com.example.theguide.ui.theme.bg1
import com.example.theguide.ui.theme.bg2
import com.example.theguide.ui.theme.softBlurOrange
import com.example.theguide.ui.theme.softOrange

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit = {}
) {
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let {
            Log.d("SignInScreen", "Error: $it")
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painterResource(id = R.drawable.bg),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize(),
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "app logo",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.padding(top=80.dp).size(140.dp),
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 340.dp),
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.login_screen_title),
                    style = Typography.headlineMedium.copy(
                        fontSize = 35.sp,
                        fontWeight = FontWeight(450),
                        fontFamily = AlegreyaFontFamily
                    ),
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    modifier = Modifier.width(Dp.Unspecified),
                    onClick = onSignInClick,
                    shape = ButtonDefaults.shape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF1F1F1F),
                    ),
                    contentPadding = PaddingValues(6.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = bg,
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .padding(start = 10.dp)
                            .size(30.dp)
                            .paint(
                                painter = painterResource(id = R.drawable.google_logo),
                                contentScale = ContentScale.Inside
                            )
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        text = stringResource(id = R.string.sign_in_with_google),
                        maxLines = 1
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    TheGuideTheme {
        SignInScreen(state = SignInState())
    }
}