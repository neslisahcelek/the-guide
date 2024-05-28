package com.example.theguide.presentation.welcome.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theguide.R
import com.example.theguide.domain.model.User
import com.example.theguide.presentation.welcome.WelcomeAction
import com.example.theguide.presentation.welcome.WelcomeState
import com.example.theguide.ui.component.RateCard
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography

@Composable
fun WelcomeScreen(
    action: (WelcomeAction) -> Unit = {},
    navigate: (String) -> Unit = {},
    state: WelcomeState,
    user: User
) {
    Surface (modifier = Modifier.fillMaxSize()) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
        ) { values ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
            ){
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.welcome_screen_title, user.displayName.substringBefore(" ")),
                    style = Typography.headlineMedium.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight(500),
                    ),
                    maxLines = 1
                )

                Text(
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.welcome_screen_text))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "${state.currentPlaceIndex+1} / ${state.placeList.size}"
                )

                Spacer(modifier = Modifier.height(5.dp))

                RateCard(
                    modifier = Modifier.padding(15.dp),
                    place = state.currentPlace,
                    action = action,
                    state = state,
                    navigate = navigate,
                    userId = user.id
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    TheGuideTheme {
        WelcomeScreen(
            state = WelcomeState(),
            user = User(
                id = "1",
                displayName = "Neslişah",
                email = "",
                picture = "",
                phoneNumber = ""
            )
        )
    }
}