package com.example.crosoftentechnicalchallenge.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crosoftentechnicalchallenge.presentation.ui.views.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun MainNavHost(navHostController: NavHostController, onLogout: () -> Unit) {
    NavHost(navController = navHostController, startDestination = MainScreenRoute ) {
        composable<MainScreenRoute> {
            HomeScreen(onLogout = onLogout)
        }
    }
}

@Serializable
object MainScreenRoute