package com.adopcion.domain.model

data class LoggedUser(
    val userId: Int,
    val username: String,
    val email: String,
    val isStaff: Boolean,
)