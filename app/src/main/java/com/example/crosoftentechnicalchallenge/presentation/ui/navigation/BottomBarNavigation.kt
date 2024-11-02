package com.example.crosoftentechnicalchallenge.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.crosoftentechnicalchallenge.presentation.ui.views.bookregister.BookRegisterScreen
import com.example.crosoftentechnicalchallenge.presentation.ui.views.details.SearchScreen
import com.example.crosoftentechnicalchallenge.presentation.ui.views.feed.FeedScreen
import com.example.crosoftentechnicalchallenge.presentation.ui.views.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Composable
fun BottomBarNavigation(navHostController: NavHostController, onLogout: () -> Unit) {
   NavHost(navController = navHostController, startDestination = FeedScreenRoute) {
       composable<FeedScreenRoute> {
           FeedScreen()
       }
       composable<SearchScreenRoute> {
           SearchScreen()
       }
       composable<BookRegisterScreenRoute> {
           BookRegisterScreen()
       }
       composable<ProfileScreenRoute> {
           ProfileScreen(onLogout)
       }
   }
}

@Serializable
object FeedScreenRoute

@Serializable
object SearchScreenRoute

@Serializable
object BookRegisterScreenRoute

@Serializable
object ProfileScreenRoute
