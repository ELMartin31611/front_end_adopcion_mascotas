package com.adopcion.domain.model

data class Fundacion(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val telefono: String,
    val correo: String,
    val isActive: Boolean,
    val createdAt: String,
)

data class FundacionPayload(
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val telefono: String,
    val correo: String,
    val isActive: Boolean,
)