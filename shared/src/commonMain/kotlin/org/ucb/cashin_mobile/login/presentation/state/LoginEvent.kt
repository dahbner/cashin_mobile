package org.ucb.cashin_mobile.login.presentation.state

sealed interface LoginEvent {
    data class OnEmailChanged(val email: String) : LoginEvent
    data class OnPasswordChanged(val password: String) : LoginEvent
    object OnSubmit : LoginEvent
    object OnBackClicked : LoginEvent
}