package org.ucb.cashin_mobile.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import cashin.shared.generated.resources.Res
import cashin.shared.generated.resources.login_back
import cashin.shared.generated.resources.login_button
import cashin.shared.generated.resources.login_email_label
import cashin.shared.generated.resources.login_logo_description
import cashin.shared.generated.resources.login_password_label
import cashin.shared.generated.resources.logo_cashin
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.cashin_mobile.login.presentation.composable.CashinColors
import org.ucb.cashin_mobile.login.presentation.composable.LabeledTextField
import org.ucb.cashin_mobile.login.presentation.composable.PrimaryButton
import org.ucb.cashin_mobile.login.presentation.state.LoginEffect
import org.ucb.cashin_mobile.login.presentation.state.LoginEvent
import org.ucb.cashin_mobile.login.presentation.viewmodel.LoginViewModel
import org.ucb.cashin_mobile.navigation.NavRoute

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToHome -> {
                    navController.navigate(NavRoute.Home) {
                        popUpTo(NavRoute.Login) { inclusive = true }
                    }
                }
                LoginEffect.NavigateBack -> {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
                is LoginEffect.ShowToast -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(message = effect.message)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = CashinColors.Background,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { viewModel.emitEvent(LoginEvent.OnBackClicked) },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 4.dp, top = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.login_back),
                    tint = CashinColors.DarkGreen,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(72.dp))

            Image(
                painter = painterResource(Res.drawable.logo_cashin),
                contentDescription = stringResource(Res.string.login_logo_description),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(width = 210.dp, height = 180.dp)
            )

            Spacer(modifier = Modifier.height(56.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                LabeledTextField(
                    label = stringResource(Res.string.login_email_label),
                    value = state.value.email,
                    onValueChange = { viewModel.emitEvent(LoginEvent.OnEmailChanged(it)) },
                    keyboardType = KeyboardType.Email,
                    enabled = !state.value.isLoading
                )

                LabeledTextField(
                    label = stringResource(Res.string.login_password_label),
                    value = state.value.password,
                    onValueChange = { viewModel.emitEvent(LoginEvent.OnPasswordChanged(it)) },
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                    enabled = !state.value.isLoading
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            PrimaryButton(
                text = stringResource(Res.string.login_button),
                onClick = { viewModel.emitEvent(LoginEvent.OnSubmit) },
                isLoading = state.value.isLoading,
                modifier = Modifier.padding(horizontal = 44.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
