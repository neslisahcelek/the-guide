package com.example.theguide.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.WelcomeTopAppBar
import com.example.theguide.ui.theme.TheGuideTheme
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    event: (WelcomeAction) -> Unit = {},
    navigate: (String) -> Unit = {}
) {
    Surface (modifier = Modifier.fillMaxSize()) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            topBar = { WelcomeTopAppBar(name = "Name") }
        ) { values ->
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ){
                Spacer(modifier = Modifier.fillMaxHeight(0.9f))

                val scope = rememberCoroutineScope()
                Row {
                    Button(
                        onClick = {
                            scope.launch {
                                event.invoke(WelcomeAction.SaveAppEntry)
                            } },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text("Enter app")
                    }
                    Button(
                        onClick = { navigate(Route.DashboardScreen.route) },
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Text("Click to get in!")
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    TheGuideTheme {
        WelcomeScreen()
    }
}