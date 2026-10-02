package org.ucb.cashin_mobile

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration
import org.ucb.cashin_mobile.di.getModules
import org.ucb.cashin_mobile.navigation.AppNavHost

@Composable
@Preview
fun App() {
    KoinApplication(
        configuration = koinConfiguration {
            modules(getModules())
        }
    ) {
        MaterialTheme {
            AppNavHost()
        }
    }
}