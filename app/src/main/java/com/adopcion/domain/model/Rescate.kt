package com.adopcion.domain.model

data class Rescate(
    val id: Int,
    val descripcion: String,
    val ubicacion: String,
    val estado: String,
    val createdAt: String,
)

data class RescatePayload(
    val descripcion: String,
    val ubicacion: String,
    val estado: String,
)