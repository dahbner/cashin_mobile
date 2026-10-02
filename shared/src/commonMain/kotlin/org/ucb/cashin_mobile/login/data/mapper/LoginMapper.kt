package org.ucb.cashin_mobile.login.data.mapper

import org.ucb.cashin_mobile.login.data.dto.LoginResponseDto
import org.ucb.cashin_mobile.login.domain.model.LoginModel

fun LoginResponseDto.toModel() = LoginModel(
    token = token ?: "",
    email = email ?: ""
)