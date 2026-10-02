package org.ucb.cashin_mobile.login.presentation.state

sealed interface LoginEffect {
    data class ShowToast(val message: String) : LoginEffect
    object NavigateToHome : LoginEffect
    object NavigateBack : LoginEffect
}