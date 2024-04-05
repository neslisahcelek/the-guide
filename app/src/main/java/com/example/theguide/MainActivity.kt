package com.example.theguide

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.example.theguide.domain.usecase.AppEntryUseCases
import com.example.theguide.domain.usecase.ReadAppEntryUseCase
import com.example.theguide.presentation.navigation.TheGuideNavGraph
import com.example.theguide.presentation.welcome.WelcomeVM
import com.example.theguide.ui.theme.TheGuideTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen() //.setKeepOnScreenCondition{  }

        /*
        lifecycleScope.launch {
            appEntryUseCases.readAppEntryUseCase().collect{
                Log.d("MainActivity", "AppEntry: $it")
            }
        }

         */

        setContent {
            TheGuideTheme {
                TheGuideNavGraph()
            }
        }

    }
}


