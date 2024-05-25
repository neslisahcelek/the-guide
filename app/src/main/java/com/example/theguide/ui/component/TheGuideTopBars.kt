package com.example.theguide.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTopAppBar(
    title: String,
    onBackClick: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = Typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                ),
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackClick.invoke() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarWithActions(
    title: String
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
            )
        },
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Button")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopAppBar() {
    LargeTopAppBar(
        title = {},
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TheGuideAppBarsPreview() {
    TheGuideTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            PrimaryTopAppBar(title = "Primary")
            AppBarWithActions(title = "AppBarWithActions")
            LargeTopAppBar()
        }
    }
}