package org.ucb.cashin_mobile.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.cashin_mobile.login.presentation.viewmodel.LoginViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
}