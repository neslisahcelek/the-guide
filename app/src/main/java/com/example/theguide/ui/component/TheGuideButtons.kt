package com.example.theguide.ui.component

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TheGuideButtons() {
    ElevatedButton(onClick = { }) {
        Text("Elevated")
    }
}

@Preview
@Composable
fun TheGuideButtonsPreview() {
    TheGuideButtons()
}