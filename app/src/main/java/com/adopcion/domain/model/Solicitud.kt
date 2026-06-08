package com.adopcion.domain.model

data class Solicitud(
    val id: Int,
    val usuario: String,
    val mascotaId: Int,
    val estado: String,
    val fecha: String,
)

data class SolicitudPayload(
    val mascotaId: Int,
    val estado: String = "Pendiente",
)