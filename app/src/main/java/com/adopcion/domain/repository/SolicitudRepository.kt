package com.adopcion.domain.repository

import com.adopcion.domain.model.Solicitud
import com.adopcion.domain.model.SolicitudPayload

interface SolicitudRepository {

    suspend fun getSolicitudes(): Result<List<Solicitud>>

    suspend fun getSolicitud(id: Int): Result<Solicitud>

    suspend fun createSolicitud(payload: SolicitudPayload): Result<Solicitud>

    suspend fun updateSolicitud(
        id: Int,
        payload: SolicitudPayload
    ): Result<Solicitud>

    suspend fun deleteSolicitud(id: Int): Result<Unit>
}