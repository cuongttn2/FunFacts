package com.example.funfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.funfacts.ui.screen.navigation.FunFactsNavigationGraph
import com.example.funfacts.ui.theme.FunFactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            FunFactsTheme {
                FunFactsApp()
            }
        }

    }

    @Composable
    fun FunFactsApp() {
        FunFactsNavigationGraph()
    }
}


