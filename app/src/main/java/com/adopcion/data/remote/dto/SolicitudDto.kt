package com.adopcion.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.adopcion.domain.model.Solicitud
import com.adopcion.domain.model.SolicitudPayload

data class SolicitudDto(
    val id: Int,
    val usuario: Int,
    val mascota: Int,
    val estado: String,
    @SerializedName("created_at") val createdAt: String,
)

data class SolicitudRequestDto(
    val usuario: Int,
    val mascota: Int,
    val estado: String,
)

// mapper
fun SolicitudDto.toDomain() = Solicitud(
    id = id,
    usuarioId = usuario,
    mascotaId = mascota,
    estado = estado,
    createdAt = createdAt,
)

fun SolicitudPayload.toRequest() = SolicitudRequestDto(
    usuario = usuarioId,
    mascota = mascotaId,
    estado = estado,
)