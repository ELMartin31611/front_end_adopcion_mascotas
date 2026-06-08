package com.adopcion.domain.model

data class Mascota(
    val id: Int,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val estado: String,
    val fundacionId: Int,
    val createdAt: String,
)

data class MascotaPayload(
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val estado: String,
    val fundacionId: Int,
)