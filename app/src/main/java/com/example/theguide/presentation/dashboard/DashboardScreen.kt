package com.example.theguide.presentation.dashboard

import android.content.res.Configuration
import android.view.Display.Mode
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.ui.component.PrimaryAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme

//import androidx.constraintlayout.compose.ConstraintLayout


@Composable
fun DashboardScreen(
    navigate: (String) -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { PrimaryAppBar(title = stringResource(id = R.string.dashboard_screen_title)) }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    item {
                        RecommendationCard(
                            image = R.drawable.walkers,
                            name = "Walkers",
                            rating = "4.5"
                        )
                    }
                    item {
                        RecommendationCard(
                            image = R.drawable.understone,
                            name = "Understone Coffee",
                            rating = "4.5"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DashboardScreenPreview() {
    TheGuideTheme {
        DashboardScreen()
    }
}