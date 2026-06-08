package com.adopcion.data.remote.dto

import com.adopcion.domain.model.Solicitud
import com.adopcion.domain.model.SolicitudPayload
import com.google.gson.annotations.SerializedName

data class SolicitudDto(
    val id: Int,
    val usuario: String,
    val mascota: Int,
    val estado: String,
    @SerializedName("fecha") val fecha: String,
)

data class SolicitudRequestDto(
    val mascota: Int? = null,
    val estado: String? = null,
)

fun SolicitudDto.toDomain() = Solicitud(
    id = id,
    usuario = usuario,
    mascotaId = mascota,
    estado = estado,
    fecha = fecha,
)

fun SolicitudPayload.toRequest() = SolicitudRequestDto(
    mascota = mascotaId,
    estado = estado,
)