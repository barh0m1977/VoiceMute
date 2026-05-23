package com.ibrahim.voicemute.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ibrahim.voicemute.presentation.home.HomeScreen
import com.ibrahim.voicemute.presentation.settings.SettingsScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen()
        }

        composable("settings") {
            SettingsScreen()
        }
    }
}
