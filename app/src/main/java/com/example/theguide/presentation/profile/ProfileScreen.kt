package com.example.theguide.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.theguide.R
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.presentation.welcome.WelcomeAction
import com.example.theguide.presentation.welcome.WelcomeState
import com.example.theguide.ui.component.PrimaryTopAppBar
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.softOrange

@Composable
fun ProfileScreen(
    action: (ProfileAction) -> Unit = {},
    navigate: (String) -> Unit = {},
    state: ProfileState,
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                Spacer(Modifier.height(30.dp))
                /*
             Image(
                 painter = painterResource(R.drawable.bg),
                 contentScale = ContentScale.FillBounds,
                 modifier = Modifier
                     .width(180.dp)
                     .height(180.dp)
                     .clip(RoundedCornerShape(90.dp)), contentDescription = ""
             )
*/

             AsyncImage(
                 model = state.profileImage,
                 contentDescription = "Profile Image",
                 contentScale = ContentScale.FillBounds,
                 modifier = Modifier
                     .width(180.dp)
                     .height(180.dp)
                     .clip(RoundedCornerShape(90.dp))
             )



                Text(
                    text = state.userName,
                    color = softOrange,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(700),
                        fontSize = 30.sp
                    )
                )

                Spacer(Modifier.height(300.dp))
                Button(onClick = {
                    action.invoke(ProfileAction.Logout)
                    navigate.invoke(Route.LoginScreen.route)
                }) {
                    Text(text = stringResource(id = R.string.signup_button)
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    TheGuideTheme {
        ProfileScreen(
            state = ProfileState(
                userName = "Neslişah Çelek"
            )
        )
    }
}