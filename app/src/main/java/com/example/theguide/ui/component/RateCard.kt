package com.example.theguide.ui.component

import Recommendation
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.theguide.R
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.presentation.welcome.WelcomeAction
import com.example.theguide.presentation.welcome.WelcomeState
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.bg
import com.example.theguide.util.Util

@Composable
fun RateCard(
    modifier: Modifier = Modifier,
    place: PlaceModel,
    action: (WelcomeAction) -> Unit = {},
    state: WelcomeState,
    navigate: (String) -> Unit = {},
    userId: String? = null
) {
    val context = LocalContext.current
    val intent = remember {
        Intent(Intent.ACTION_VIEW).apply {
            data = android.net.Uri.parse(place.mapsUrl)
            setPackage("com.google.android.apps.maps")
        }
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface

    ),
        modifier = modifier
            .clickable { context.startActivity(intent) }
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = place.placeName,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            AsyncImage(
                model = place.photos.first(),
                contentDescription = "Place Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .height(180.dp),
                placeholder = painterResource(id = R.drawable.understone)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.rate_card_question),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            RatingSlider(userId = userId, action = action, state = state, navigate = navigate)
        }
    }
}

@Composable
fun RatingSlider(
    action: (WelcomeAction) -> Unit = {},
    state: WelcomeState,
    navigate: (String) -> Unit = {},
    userId: String? = null
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = sliderPosition.toInt().toString(),
            modifier = Modifier.padding(start = 10.dp),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Slider(
            modifier = Modifier.padding(start = 5.dp, end = 5.dp),
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = bg,
            ),
            steps = 4,
            valueRange = 0f..5f,
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                action.invoke(
                    WelcomeAction.RatePlace(
                        userId = userId ?: "",
                        placeId = 1, //state.currentPlace.id
                        rating = sliderPosition.toDouble()
                    )
                )
            },
            modifier = Modifier.wrapContentSize()
        ) {
            if (state.isListCompleted) {
                navigate.invoke(Route.DashboardScreen.route)
            }
            Text(stringResource(id = R.string.rate_card_button))
        }
    }
}

@Preview
@Composable
fun RateCardPreview() {
    TheGuideTheme {
        RateCard(
            place = Util.getPlace(),
            action = {},
            state = WelcomeState()
        )
    }
}