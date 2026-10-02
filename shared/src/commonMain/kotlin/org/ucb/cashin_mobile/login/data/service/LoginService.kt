package org.ucb.cashin_mobile.login.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.cashin_mobile.login.data.datasource.LoginRemoteDatasource
import org.ucb.cashin_mobile.login.data.dto.LoginRequestDto
import org.ucb.cashin_mobile.login.data.dto.LoginResponseDto

class LoginService : LoginRemoteDatasource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun login(request: LoginRequestDto): LoginResponseDto {
        val response = client.post("https://api.ucb.cashin_mobile.com/v1/auth/login") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        try {
            val body = response.body<LoginResponseDto>()
            return body
        } catch (e: Exception) {
            throw e
        }
    }
}