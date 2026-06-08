package com.adopcion.domain.model

data class Donacion(
    val id: Int,
    val usuarioId: Int,
    val fundacionId: Int,
    val monto: Double,
    val fecha: String,
)