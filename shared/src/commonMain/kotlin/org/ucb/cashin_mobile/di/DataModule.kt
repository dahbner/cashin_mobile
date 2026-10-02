package org.ucb.cashin_mobile.di


import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.ucb.cashin_mobile.login.data.datasource.LoginRemoteDatasource
import org.ucb.cashin_mobile.login.data.repository.AuthenticationRepositoryImpl
import org.ucb.cashin_mobile.login.data.service.LoginService
import org.ucb.cashin_mobile.login.domain.repository.AuthenticationRepository

val dataModule = module {
    singleOf(::LoginService).bind<LoginRemoteDatasource>()
    singleOf(::AuthenticationRepositoryImpl).bind<AuthenticationRepository>()
}