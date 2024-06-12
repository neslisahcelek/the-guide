package com.example.theguide.ui.component

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun RecommendationCard(
    modifier: Modifier = Modifier,
    place: PlaceModel,
    onAddToWishList: () -> Unit = {},
    onRemoveFromWishList: () -> Unit = {},
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
        Row(modifier = Modifier.fillMaxHeight().padding(end=8.dp)) {
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
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    model = if (place.photos.isNotEmpty()) {
                        place.photos.first()
                    } else { },
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
                    placeholder = painterResource(id = R.drawable.logo)
                )

                val initialIsInList = false
                var isInList by remember {
                    mutableStateOf(initialIsInList)
                }
                Icon(
                    imageVector =
                    if (isInList) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    },
                    contentDescription = "Add to wish list",
                    tint = Color.Red,
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            if (!isInList) {
                                onAddToWishList()
                                isInList = true
                            } else {
                                onRemoveFromWishList()
                                isInList = false
                            }
                        }
                        .size(20.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.expected_score, place.expectedScore),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.width(8.dp))

            }

            /*
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.average_score, place.rating),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
            }

             */
        }
    }
}

@Preview
@Composable
fun RecommendationCardPreview() {
    TheGuideTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp)
        ) {
            RecommendationCard(
                place = Util.getPlace(),
            )
        }
    }
}