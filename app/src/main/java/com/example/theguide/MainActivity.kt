package com.example.theguide

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.theguide.presentation.dashboard.DashboardScreen
import com.example.theguide.presentation.dashboard.DashboardVM
import com.example.theguide.presentation.login.GoogleAuthUIClient
import com.example.theguide.presentation.login.SignInScreen
import com.example.theguide.presentation.login.SignInVM
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.presentation.profile.ProfileScreen
import com.example.theguide.presentation.profile.ProfileVM
import com.example.theguide.presentation.topplaces.TopPlacesScreen
import com.example.theguide.presentation.topplaces.TopPlacesVM
import com.example.theguide.presentation.welcome.WelcomeScreen
import com.example.theguide.presentation.welcome.WelcomeVM
import com.example.theguide.ui.theme.TheGuideTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val googleAuthUIClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen() //.setKeepOnScreenCondition{  }
        /*
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            )
        )

         */

        setContent {
            TheGuideTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Route.LoginScreen.route
                    ) {
                        composable(Route.LoginScreen.route) {
                            val viewModel: SignInVM = hiltViewModel()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(Unit) {
                                if (googleAuthUIClient.getSignedInUser() != null) {
                                    navController.navigate(Route.DashboardScreen.route)
                                }
                            }

                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = googleAuthUIClient.signInWithIntent(
                                                result.data ?: return@launch
                                            )
                                            viewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Log.d("MainActivity", "isSignInSuccessful")
                                    navController.navigate(Route.WelcomeScreen.route)
                                    viewModel.resetState()
                                }
                            }

                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = googleAuthUIClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            )
                        }

                        composable(Route.WelcomeScreen.route) {
                            val viewModel: WelcomeVM = hiltViewModel()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(Unit) {
                                viewModel.loadUserInfoIfNeeded()
                            }

                            WelcomeScreen(
                                action = viewModel::onAction,
                                navigate = { route ->
                                    navController.navigate(route)
                                },
                                state = state
                            )
                        }

                        composable(Route.DashboardScreen.route) {
                            val viewModel: DashboardVM = hiltViewModel()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            DashboardScreen(
                                action = viewModel::onAction,
                                state = state,
                                navigate = { route ->
                                    navController.navigate(route)
                                })
                        }

                        composable(Route.TopPlacesScreen.route) {
                            val viewModel: TopPlacesVM = hiltViewModel()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            TopPlacesScreen(
                                action = viewModel::onAction,
                                state = state,
                                navigate = { route ->
                                    navController.navigate(route)
                                })
                        }

                        composable(Route.ProfileScreen.route) {
                            val viewModel: ProfileVM = hiltViewModel()
                            val state by viewModel.state.collectAsStateWithLifecycle()

                            ProfileScreen(
                                action = viewModel::onAction,
                                state = state,
                                navigate = { route ->
                                    navController.navigate(route)
                                },
                                user = googleAuthUIClient.getSignedInUser()
                            ) {
                                lifecycleScope.launch {
                                    googleAuthUIClient.signOut()
                                    navController.navigate(Route.LoginScreen.route)
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}


