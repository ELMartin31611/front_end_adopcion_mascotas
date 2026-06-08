package com.adopcion.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.adopcion.domain.model.Donacion
import com.adopcion.domain.model.DonacionPayload

data class DonacionDto(
    val id: Int,
    val monto: Double,

    @SerializedName("usuario")
    val usuarioId: Int,

    @SerializedName("fundacion")
    val fundacionId: Int,

    @SerializedName("created_at")
    val fecha: String,
)

data class DonacionRequestDto(
    val monto: Double,
    val fundacion: Int,
)

fun DonacionDto.toDomain() = Donacion(
    id = id,
    usuarioId = usuarioId,
    fundacionId = fundacionId,
    monto = monto,
    fecha = fecha,
)

fun DonacionPayload.toRequest() = DonacionRequestDto(
    monto = monto,
    fundacion = fundacionId,
)