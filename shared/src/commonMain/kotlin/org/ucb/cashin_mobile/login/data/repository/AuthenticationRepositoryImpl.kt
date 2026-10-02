package org.ucb.cashin_mobile.login.data.repository

import org.ucb.cashin_mobile.login.data.datasource.LoginRemoteDatasource
import org.ucb.cashin_mobile.login.data.dto.LoginRequestDto
import org.ucb.cashin_mobile.login.data.mapper.toModel
import org.ucb.cashin_mobile.login.domain.model.LoginModel
import org.ucb.cashin_mobile.login.domain.repository.AuthenticationRepository

class AuthenticationRepositoryImpl(
    val remote: LoginRemoteDatasource
) : AuthenticationRepository {

    override suspend fun login(email: String, password: String): Result<LoginModel> {
        return try {
            val request = LoginRequestDto(email, password)
            val response = remote.login(request)
            Result.success(response.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}