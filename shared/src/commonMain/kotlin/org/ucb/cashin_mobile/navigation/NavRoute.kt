package org.ucb.cashin_mobile.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavRoute {
    @Serializable
    object Login : NavRoute()

    @Serializable
    object Home : NavRoute()

    @Serializable
    object Expenses : NavRoute()
}
