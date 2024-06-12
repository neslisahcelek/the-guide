package com.example.theguide

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.theguide.presentation.dashboard.DashboardAction
import com.example.theguide.presentation.dashboard.views.DashboardScreen
import com.example.theguide.presentation.dashboard.DashboardVM
import com.example.theguide.presentation.signin.GoogleAuthUIClient
import com.example.theguide.presentation.signin.SignInScreen
import com.example.theguide.presentation.signin.SignInVM
import com.example.theguide.presentation.navigation.Route
import com.example.theguide.presentation.profile.ProfileScreen
import com.example.theguide.presentation.profile.ProfileVM
import com.example.theguide.presentation.topplaces.TopPlacesScreen
import com.example.theguide.presentation.topplaces.TopPlacesVM
import com.example.theguide.presentation.visitedlist.VisitedListVM
import com.example.theguide.presentation.visitedlist.views.VisitedListScreen
import com.example.theguide.presentation.welcome.views.WelcomeScreen
import com.example.theguide.presentation.welcome.WelcomeVM
import com.example.theguide.presentation.wishlist.WishListScreen
import com.example.theguide.presentation.wishlist.WishListVM
import com.example.theguide.ui.component.TheGuideBottomBar
import com.example.theguide.ui.component.TheGuideTopBar
import com.example.theguide.ui.component.TopBarActions
import com.example.theguide.ui.theme.TheGuideTheme
import com.example.theguide.ui.theme.softOrange
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val googleAuthUIClient by lazy {
        GoogleAuthUIClient(
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen() //.setKeepOnScreenCondition{  }
        //enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))
        setContent {
            TheGuideTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(softOrange)
                ) {
                    val navController = rememberNavController()

                    /*
                    LaunchedEffect(Unit) {
                        window.decorView.systemUiVisibility = (
                                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                )
                    }
                     */
                    var isFilterClicked by remember { mutableStateOf(false) }

                    val actions = TopBarActions(
                        onFilterClick = {
                            isFilterClicked = !isFilterClicked
                        },
                        onProfileClick = {
                            navController.navigate(Route.ProfileScreen.route)
                        }
                    )
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        topBar = {
                            val currentDestination =
                                navController.currentBackStackEntryAsState().value?.destination?.route
                            if (currentDestination != Route.LoginScreen.route && currentDestination != Route.WelcomeScreen.route) {
                                TheGuideTopBar(navController = navController, actions = actions)
                            }
                        },
                        bottomBar = {
                            val currentDestination =
                                navController.currentBackStackEntryAsState().value?.destination?.route
                            if (currentDestination != Route.LoginScreen.route && currentDestination != Route.WelcomeScreen.route) {
                                TheGuideBottomBar(navController = navController)
                            }
                        }
                    ) {
                        NavHost(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
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
                                                val signInResult =
                                                    googleAuthUIClient.signInWithIntent(
                                                        result.data ?: return@launch
                                                    )
                                                viewModel.onSignInResult(signInResult)
                                            }
                                        }
                                    }
                                )

                                LaunchedEffect(key1 = state.isSignInSuccessful) {
                                    if (state.isSignInSuccessful) {
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

                                WelcomeScreen(
                                    action = viewModel::onAction,
                                    navigate = { route ->
                                        navController.navigate(route)
                                    },
                                    state = state,
                                    user = googleAuthUIClient.getSignedInUser() ?: return@composable
                                )
                            }

                            composable(Route.DashboardScreen.route) {
                                val viewModel: DashboardVM = hiltViewModel()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                DashboardScreen(
                                    action = viewModel::onAction,
                                    state = state,
                                    user = googleAuthUIClient.getSignedInUser(),
                                    isFilterClicked = isFilterClicked
                                )
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
                                    user = googleAuthUIClient.getSignedInUser(),
                                    onSignOut = {
                                        lifecycleScope.launch {
                                            googleAuthUIClient.signOut()
                                            navController.navigate(Route.LoginScreen.route)
                                        }
                                    }
                                )
                            }

                            composable(Route.WishListScreen.route) {
                                val viewModel: WishListVM = hiltViewModel()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                WishListScreen(
                                    state = state,
                                    navigate = { route ->
                                        navController.navigate(route)
                                    },
                                    action = viewModel::onAction,
                                    user = googleAuthUIClient.getSignedInUser()
                                )
                            }

                            composable(Route.VisitedListScreen.route) {
                                val viewModel: VisitedListVM = hiltViewModel()
                                val state by viewModel.state.collectAsStateWithLifecycle()

                                VisitedListScreen(
                                    state = state,
                                    navigate = { route ->
                                        navController.navigate(route)
                                    },
                                    action = viewModel::onAction,
                                    user = googleAuthUIClient.getSignedInUser()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}




