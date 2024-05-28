package com.example.theguide.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.theguide.R
import com.example.theguide.data.local.UserEntity
import com.example.theguide.domain.model.User
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.softOrange

@Composable
fun ProfileScreen(
    action: (ProfileAction) -> Unit = {},
    navigate: (String) -> Unit = {},
    state: ProfileState,
    user: User? = null,
    onSignOut: () -> Unit = {}
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                PrimaryTopAppBar(
                    title = stringResource(id = R.string.profile_screen_title),
                    onBackClick = { navigate.invoke(Route.DashboardScreen.route) }
                )
            }
        ) { values ->
            Box {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(values)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(MaterialTheme.colorScheme.surface),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(40.dp)
                ) {
                    /*
                    Image(
                        painter = painterResource(R.drawable.bg),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .width(180.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(90.dp)), contentDescription = ""

                    )
                    */

                    AsyncImage(
                        model = user?.picture,
                        contentDescription = "Profile Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .width(180.dp)
                            .height(180.dp)
                            .clip(RoundedCornerShape(90.dp))
                    )



                    Text(
                        text = user?.displayName ?: "",
                        color = softOrange,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight(700),
                            fontSize = 30.sp
                        )
                    )

                    ProfileRow(
                        title = stringResource(id = R.string.profile_email),
                        value = user?.email ?: ""
                    )

                    ProfileRow(
                        title = stringResource(id = R.string.profile_city),
                        value = state.city
                    )

                    Button(onClick = { navigate.invoke(Route.WishListScreen.route) }) {
                        Text(text = "Gitmek istediğin yerler")
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 80.dp)
                ) {
                    Button(
                        onClick = {
                            onSignOut.invoke()
                            action.invoke(ProfileAction.Logout)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.signup_button)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileRow(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(300),
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.LightGray))
        Text(
            text = value,
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight(500),
                fontSize = 16.sp
            )
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    TheGuideTheme {
        ProfileScreen(
            state = ProfileState(
                userName = "",
                user = UserEntity(
                    firstName = "",
                    lastName = "",
                    googleTokenId = "",
                    imageUrl = "",
                    id = "",
                    email = ""
                )
            ),
            user = User(
                id = "1",
                displayName = "Neslişah Çelek",
                email = "neslisah.celek@outlook.com",
                picture = "",
                phoneNumber = "05554443388"
            )
        )
    }
}