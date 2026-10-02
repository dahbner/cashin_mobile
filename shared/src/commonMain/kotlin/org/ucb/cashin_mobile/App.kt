package org.ucb.cashin_mobile

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration
import org.ucb.cashin_mobile.di.getModules
import org.ucb.cashin_mobile.login.presentation.screen.LoginScreen
import org.ucb.cashin_mobile.navigation.NavRoute

@Composable
@Preview
fun App() {
    KoinApplication(
        configuration = koinConfiguration {
            modules(getModules())
        }
    ) {
        MaterialTheme {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavRoute.Login
            ) {
                composable<NavRoute.Login> {
                    LoginScreen(navController = navController)
                }
                composable<NavRoute.Home> {
                    // Placeholder for Home screen
                }
            }
        }
    }
}