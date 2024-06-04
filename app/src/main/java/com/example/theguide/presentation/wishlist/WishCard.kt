package com.example.theguide.presentation.wishlist

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import com.example.theguide.ui.theme.bg
import com.example.theguide.ui.theme.softOrange
import com.example.theguide.ui.theme.yellow
import com.example.theguide.util.Util

@Composable
fun WishCard(
    action: (WishListAction) -> Unit = {},
    userId: String? = null,
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
        Row(modifier = Modifier
            .fillMaxHeight()
            .padding(end = 8.dp)) {
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
            Box {
                AsyncImage(
                    model = if (place.photos.isNotEmpty()) {
                        place.photos.first()
                    } else {
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQR_abBtnzBFl_-kLkB-fbC-nskMexTTiE7w9GroVJTGA&s"
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
                    placeholder = painterResource(id = R.drawable.understone)
                )

                val initialIsInList = true
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

            Text(
                text = stringResource(id = R.string.wish_card_add_rating),
                modifier = Modifier.padding(top = 10.dp),
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = MaterialTheme.typography.titleMedium,
            )

            var addRatingButtonClicked by remember {
                mutableStateOf(false)
            }

            if (!addRatingButtonClicked) {
                OutlinedButton(
                    onClick = {
                        addRatingButtonClicked = true
                    },
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = Brush.sweepGradient(
                            colors = listOf(
                                softOrange,
                                softOrange,
                            )
                        )
                    ),
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .padding(vertical = 8.dp),
                    contentPadding = PaddingValues(4.dp),
                ) {
                    Text(
                        text = stringResource(id = R.string.wish_card_button),
                        fontSize = 16.sp,
                    )
                }
            } else {
                var sliderPosition by remember { mutableFloatStateOf(0f) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = sliderPosition.toInt().toString(),
                        modifier = Modifier.padding(start = 10.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Slider(
                        modifier = Modifier.padding(horizontal = 5.dp),
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

                Button(
                    onClick = {
                        action.invoke(
                            WishListAction.RatePlace(
                                userId = userId ?: "",
                                place = place,
                                rating = sliderPosition.toDouble()
                            )
                        )
                        addRatingButtonClicked = false
                    },
                    modifier = Modifier.wrapContentSize().padding(bottom = 10.dp),
                ) {
                    Text(stringResource(id = R.string.rate_card_button))
                }
            }
            
        }
    }
}

@Preview
@Composable
fun WishCardPreview() {
    TheGuideTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp)
        ) {
            WishCard(
                place = Util.getPlace(),
            )
        }
    }
}