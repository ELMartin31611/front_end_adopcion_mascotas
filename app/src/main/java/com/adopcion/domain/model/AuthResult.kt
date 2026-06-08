package com.adopcion.domain.model

data class AuthResult(
    val user: LoggedUser,
    val accessToken: String,
    val refreshToken: String,
)