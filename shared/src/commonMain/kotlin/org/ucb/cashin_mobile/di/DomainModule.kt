package org.ucb.cashin_mobile.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.cashin_mobile.login.domain.usecase.DoLoginUseCase

val domainModule = module {
    singleOf(::DoLoginUseCase)
}