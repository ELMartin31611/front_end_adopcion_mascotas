package com.adopcion.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.adopcion.domain.model.Mascota
import com.adopcion.domain.model.MascotaPayload

data class MascotaDto(
    val id: Int,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val estado: String,
    val fundacion: Int,
    @SerializedName("created_at") val createdAt: String,
)

data class MascotaRequestDto(
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val descripcion: String,
    val estado: String,
    val fundacion: Int,
)

// mapper
fun MascotaDto.toDomain() = Mascota(
    id = id,
    nombre = nombre,
    especie = especie,
    raza = raza,
    edad = edad,
    descripcion = descripcion,
    estado = estado,
    fundacionId = fundacion,
    createdAt = createdAt,
)

fun MascotaPayload.toRequest() = MascotaRequestDto(
    nombre = nombre,
    especie = especie,
    raza = raza,
    edad = edad,
    descripcion = descripcion,
    estado = estado,
    fundacion = fundacionId,
)