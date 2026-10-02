package org.ucb.cashin_mobile.login.domain.usecase

import org.ucb.cashin_mobile.login.domain.model.LoginModel
import org.ucb.cashin_mobile.login.domain.repository.AuthenticationRepository

class DoLoginUseCase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<LoginModel> {
        return repository.login(email, password)
    }
}