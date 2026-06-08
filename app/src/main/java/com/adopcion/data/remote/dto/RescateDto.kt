package com.adopcion.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.adopcion.domain.model.Rescate
import com.adopcion.domain.model.RescatePayload

data class RescateDto(
    val id: Int,
    val descripcion: String,
    val ubicacion: String,
    val estado: String,
    @SerializedName("created_at") val createdAt: String,
)

data class RescateRequestDto(
    val descripcion: String,
    val ubicacion: String,
    val estado: String,
)

// mapper
fun RescateDto.toDomain() = Rescate(
    id = id,
    descripcion = descripcion,
    ubicacion = ubicacion,
    estado = estado,
    createdAt = createdAt,
)

fun RescatePayload.toRequest() = RescateRequestDto(
    descripcion = descripcion,
    ubicacion = ubicacion,
    estado = estado,
)