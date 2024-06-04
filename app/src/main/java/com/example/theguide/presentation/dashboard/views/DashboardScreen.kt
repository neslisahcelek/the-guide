package com.example.theguide.presentation.dashboard.views

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.domain.model.User
import com.example.theguide.presentation.dashboard.CheckboxState
import com.example.theguide.presentation.dashboard.DashboardAction
import com.example.theguide.presentation.dashboard.DashboardState
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.DashboardTopAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.util.Util

@Composable
fun DashboardScreen(
    action: (DashboardAction) -> Unit = {},
    state: DashboardState = DashboardState(),
    navigate: (String) -> Unit = {},
    user: User? = null
) {
    LaunchedEffect(key1 = Unit) {
        action.invoke(DashboardAction.LoadDashboard(user))
    }

    val districts = remember {
        mutableStateListOf(
            CheckboxState(
                text = "Konyaaltı",
                isChecked = false
            ),
            CheckboxState(
                text = "Muratpaşa",
                isChecked = false
            ),
            CheckboxState(
                text = "Kepez",
                isChecked = false
            ),
        )
    }

    var isFilterClicked by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                DashboardTopAppBar(
                    title = stringResource(id = R.string.dashboard_screen_title),
                    onFilterClick = {
                        if (isFilterClicked) {
                            isFilterClicked = false
                            action.invoke(DashboardAction.FilterDistricts(districts, user?.id))
                        } else {
                            isFilterClicked = true
                        }
                    },
                    onProfileClick = { navigate.invoke(Route.ProfileScreen.route) }
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(MaterialTheme.colorScheme.surface)
            ) {
                if (state.isLoading) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "loading",
                        modifier = Modifier.padding(top = 200.dp).size(200.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.loading), textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        color = Color.Black
                    )
                } else {
                    Box {
                        LazyColumn(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(10.dp),
                        ) {
                            items(state.places) { place ->
                                RecommendationCard(
                                    place = place,
                                    onAddToWishList = {
                                        action.invoke(
                                            DashboardAction.AddToWishList(
                                                userId = user?.id,
                                                place = place
                                            )
                                        )
                                    },
                                    onRemoveFromWishList = {
                                        action.invoke(
                                            DashboardAction.RemoveFromWishList(
                                                userId = user?.id,
                                                place = place
                                            )
                                        )
                                    },
                                    intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = Uri.parse(place.mapsUrl)
                                        setPackage("com.google.android.apps.maps")
                                    }
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.End,
                        ) {
                            if (isFilterClicked) {
                                DistrictCheckbox(districts)
                            }
                        }
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
        DashboardScreen(
            state = DashboardState(
                places = Util.getPlaceList()
            )
        )
    }
}