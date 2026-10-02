package org.ucb.cashin_mobile.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.cashin_mobile.login.domain.usecase.DoLoginUseCase
import org.ucb.cashin_mobile.login.presentation.state.LoginEffect
import org.ucb.cashin_mobile.login.presentation.state.LoginEvent
import org.ucb.cashin_mobile.login.presentation.state.LoginUiState

class LoginViewModel(
    private val doLoginUseCase: DoLoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LoginEffect>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> {
                _state.update { it.copy(email = event.email, error = null) }
            }
            is LoginEvent.OnPasswordChanged -> {
                _state.update { it.copy(password = event.password, error = null) }
            }
            LoginEvent.OnBackClicked -> {
                emitEffect(LoginEffect.NavigateBack)
            }
            LoginEvent.OnSubmit -> {
                submit()
            }
        }
    }

    private fun submit() {
        if (state.value.isLoading) return

        var isValid = true
        if (state.value.email.isBlank()) {
            emitEffect(LoginEffect.ShowToast("El correo es requerido"))
            isValid = false
        } else if (state.value.password.isBlank()) {
            emitEffect(LoginEffect.ShowToast("La contraseña es requerida"))
            isValid = false
        }

        if (isValid) {
            login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val result = doLoginUseCase(
                email = state.value.email.trim(),
                password = state.value.password
            )

            result
                .onSuccess {
                    _state.update { it.copy(isLoading = false) }
                    _effect.emit(LoginEffect.NavigateToHome)
                }
                .onFailure { exception ->
                    val message = exception.message ?: "No se pudo iniciar sesión"
                    _state.update { it.copy(isLoading = false, error = message) }
                    _effect.emit(LoginEffect.ShowToast("Credenciales incorrectas o error de conexión"))
                }
        }
    }

    private fun emitEffect(effect: LoginEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
