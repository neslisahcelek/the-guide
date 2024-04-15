package com.example.theguide.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.ui.component.PrimaryAppBar
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    event: (WelcomeEvent) -> Unit = {}
) {
    Surface (modifier = Modifier.fillMaxSize()) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            topBar = { PrimaryAppBar(title = stringResource(id = R.string.welcome_screen_title)) }
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
                Button(
                    onClick = {
                        scope.launch {
                            event.invoke(WelcomeEvent.SaveAppEntry)
                        } },
                    modifier = Modifier.wrapContentSize()
                ) {
                    Text("Click to get in!")
                }
            }
        }
    }
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}