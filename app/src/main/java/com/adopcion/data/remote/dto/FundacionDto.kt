package com.adopcion.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.adopcion.domain.model.Fundacion
import com.adopcion.domain.model.FundacionPayload

data class FundacionDto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val telefono: String,
    val correo: String,
    @SerializedName("is_active") val isActive: Boolean,
    @SerializedName("created_at") val createdAt: String,
)

data class FundacionRequestDto(
    val nombre: String,
    val descripcion: String,
    val direccion: String,
    val telefono: String,
    val correo: String,
    @SerializedName("is_active") val isActive: Boolean,
)

// mapper
fun FundacionDto.toDomain() = Fundacion(
    id = id,
    nombre = nombre,
    descripcion = descripcion,
    direccion = direccion,
    telefono = telefono,
    correo = correo,
    isActive = isActive,
    createdAt = createdAt,
)

fun FundacionPayload.toRequest() = FundacionRequestDto(
    nombre = nombre,
    descripcion = descripcion,
    direccion = direccion,
    telefono = telefono,
    correo = correo,
    isActive = isActive,
)