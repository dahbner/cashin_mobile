package org.ucb.cashin_mobile.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ucb.cashin_mobile.home.presentation.screen.ConsejoScreen
import org.ucb.cashin_mobile.home.presentation.screen.DesafiosScreen
import org.ucb.cashin_mobile.home.presentation.screen.HomeScreen
import org.ucb.cashin_mobile.home.presentation.screen.PreguntaScreen
import org.ucb.cashin_mobile.login.presentation.screen.LoginScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home,
        modifier = Modifier.fillMaxSize()
    ) {
        composable<NavRoute.Login> {
            LoginScreen(navController = navController)
        }
        composable<NavRoute.Home> {
            HomeScreen(navController = navController)
        }
        composable<NavRoute.Consejo> {
            ConsejoScreen(navController = navController)
        }
        composable<NavRoute.Pregunta> {
            PreguntaScreen(navController = navController)
        }
        composable<NavRoute.Desafios> {
            DesafiosScreen(navController = navController)
        }
    }
}
