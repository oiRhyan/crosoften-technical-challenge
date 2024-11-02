package com.example.crosoftentechnicalchallenge.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crosoftentechnicalchallenge.data.utils.helpers.PreferencesHelper
import com.example.crosoftentechnicalchallenge.presentation.ui.navigation.MainNavHost
import com.example.crosoftentechnicalchallenge.presentation.ui.views.auth.login.LoginScreen
import com.example.crosoftentechnicalchallenge.presentation.ui.views.auth.register.RegisterScreen

class MainActivity : ComponentActivity() {

    private var isLoggedIn by mutableStateOf(false)

    fun logout() {
        PreferencesHelper.clearToken(this)
        isLoggedIn = false
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        isLoggedIn = PreferencesHelper.getToken(this) != null

        setContent {
            val navController = rememberNavController()

            if (isLoggedIn) {
                MainNavHost(navHostController = navController, onLogout = { logout() })
            } else {
                NavHost(navController = navController, startDestination = "login") {
                    composable("register") { RegisterScreen(navHostController = navController) }
                    composable("login") {
                        LoginScreen(navHostController = navController) { token ->
                            PreferencesHelper.saveToken(this@MainActivity, token)
                            isLoggedIn = true
                        }
                    }
                }
            }
        }
    }
}

