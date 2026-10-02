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

    @Serializable
    object Consejo : NavRoute()

    @Serializable
    object Pregunta : NavRoute()

    @Serializable
    object Desafios : NavRoute()
}
