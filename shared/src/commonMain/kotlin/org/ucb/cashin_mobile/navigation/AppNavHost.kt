package org.ucb.cashin_mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ucb.cashin_mobile.home.presentation.screen.HomeScreen
import org.ucb.cashin_mobile.login.presentation.screen.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Login) {
        composable<NavRoute.Login> {
            LoginScreen(navController = navController)
        }
        composable<NavRoute.Home> {
            HomeScreen()
        }
    }
}
