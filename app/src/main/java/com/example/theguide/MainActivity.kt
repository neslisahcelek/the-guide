package com.example.theguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.theguide.ui.theme.SeniorDesignProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen() //.setKeepOnScreenCondition{  }

        setContent {
            SeniorDesignProjectTheme {
                SeniorDesignApp()
            }
        }

    }

    @Composable
    fun SeniorDesignApp() {

    }



}


