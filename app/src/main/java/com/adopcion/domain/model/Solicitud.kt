package com.adopcion.domain.model

data class Solicitud(
    val id: Int,
    val usuarioId: Int,
    val mascotaId: Int,
    val estado: String,
    val createdAt: String,
)

data class SolicitudPayload(
    val usuarioId: Int,
    val mascotaId: Int,
    val estado: String,
)