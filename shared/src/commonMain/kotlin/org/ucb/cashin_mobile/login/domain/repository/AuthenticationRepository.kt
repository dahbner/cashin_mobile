package org.ucb.cashin_mobile.login.domain.repository

import org.ucb.cashin_mobile.login.domain.model.LoginModel

interface AuthenticationRepository {
    suspend fun login(email: String, password: String): Result<LoginModel>
}