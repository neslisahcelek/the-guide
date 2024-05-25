package com.example.theguide.presentation.wishlist

import Recommendation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.theguide.R
import com.example.theguide.domain.model.User
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.component.RecommendationCard
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.util.Util

@Composable
fun WishListScreen(
    user: User?,
    navigate: (String) -> Unit = {},
    action: (WishListAction) -> Unit = {},
    state: WishListState = WishListState()
) {
    LaunchedEffect(key1 = Unit) {
        action.invoke(WishListAction.LoadWishList(user?.id ?: "1"))
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                PrimaryTopAppBar(
                    title = stringResource(id = R.string.wishlist_screen_title),
                    onBackClick = { navigate.invoke(Route.ProfileScreen.route) }
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
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(10.dp),
                ) {
                    items(state.wishList) { place ->
                        RecommendationCard(
                            place = place,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun WishListScreenPreview() {
    TheGuideTheme {
        WishListScreen(
            user = User(
                id = "1",
                displayName = "Neslişah Çelek",
                email = "neslisah.celek@outlook.com",
                picture = "",
                phoneNumber = "05554443388"
            ),
            state = WishListState(
                wishList = Util.getPlaceList()
            )
        )
    }
}