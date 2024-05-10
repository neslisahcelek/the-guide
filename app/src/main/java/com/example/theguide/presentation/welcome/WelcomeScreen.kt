package com.example.theguide.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.theguide.R
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.component.RateCard
import com.example.theguide.ui.component.WelcomeTopAppBar
import com.example.theguide.ui.theme.TheGuideTheme
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    action: (WelcomeAction) -> Unit = {},
    navigate: (String) -> Unit = {}
) {
    val viewModel: WelcomeVM = hiltViewModel()
    val state = viewModel.state

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
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    text = "Hoşgeldin, sana daha iyi öneriler sunabilmemiz için " +
                        "şehrinin en iyi mekanlarına puan ver.")

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
                    state = state
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    TheGuideTheme {
        WelcomeScreen()
    }
}