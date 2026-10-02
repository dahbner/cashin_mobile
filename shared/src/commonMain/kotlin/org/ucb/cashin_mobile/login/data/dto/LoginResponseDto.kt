package org.ucb.cashin_mobile.login.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val token: String? = null,
    val email: String? = null
)