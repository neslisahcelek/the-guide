package com.example.theguide.presentation.visitedlist.views

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.theguide.R
import com.example.theguide.domain.model.PlaceModel
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.Typography
import com.example.theguide.ui.theme.yellow
import com.example.theguide.util.Util
import kotlin.math.roundToInt

@Composable
fun VisitedPlaceCard(
    place: PlaceModel,
    intent: Intent = Intent(Intent.ACTION_VIEW)
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(35.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = place.placeName,
            modifier = Modifier.weight(0.7f),
            maxLines = 1,
            overflow = TextOverflow.Clip,
            color = Color.Black,
            style = Typography.displaySmall.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight(500),
            )
        )
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(end = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Rating",
                tint = yellow,
                modifier = Modifier.size(25.dp),
            )
            Text(
                text = place.rating.toString(),
                color = Color.Black,
                style = Typography.displaySmall.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                )
            )
        }
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = if (place.photos.isNotEmpty()) {
                    place.photos.first()
                } else {
                   Util.logoUrl
                },
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .background(Color.Black)
                    .clickable {
                        context.startActivity(intent)
                    }
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                contentDescription = "Place Image",
                //placeholder = painterResource(id = R.drawable.logo)
            )

            Text(
                text = stringResource(
                    id = R.string.visited_place_rating,
                    place.userRating.roundToInt()
                ),
                fontWeight = FontWeight(500),
                color = Color.Black,
                modifier = Modifier.padding(10.dp),
            )
        }
    }
}

@Preview
@Composable
fun VisitedPlaceCardPreview() {
    TheGuideTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp)
        ) {
            VisitedPlaceCard(
                place = Util.getPlace(),
            )
        }
    }
}