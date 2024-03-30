package com.example.theguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.theguide.presentation.navigation.TheGuideNavGraph
import com.example.theguide.ui.theme.TheGuideTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() //.setKeepOnScreenCondition{  }

        setContent {
            TheGuideTheme {
                TheGuideNavGraph()
            }
        }

    }
}


