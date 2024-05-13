package com.example.theguide.presentation.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theguide.R
import com.example.theguide.ui.component.RateCard
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography

@Composable
fun WelcomeScreen(
    action: (WelcomeAction) -> Unit = {},
    navigate: (String) -> Unit = {},
    state: WelcomeState,
) {
    Surface (modifier = Modifier.fillMaxSize()) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
        ) { values ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            ){
                Spacer(modifier = Modifier.fillMaxHeight(0.1f))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.welcome_screen_title, state.userName),
                    style = Typography.headlineMedium.copy(
                        fontSize = 28.sp,
                        fontWeight = FontWeight(500),
                    )
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
                    modifier = Modifier.padding(5.dp),
                    place = state.currentPlace,
                    action = action,
                    state = state,
                    navigate = navigate
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
            state = WelcomeState()
        )
    }
}