package com.adopcion.domain.repository

import com.adopcion.domain.model.Solicitud

interface SolicitudRepository {

    suspend fun getSolicitudes(): Result<List<Solicitud>>

    suspend fun getSolicitud(id: Int): Result<Solicitud>

    suspend fun createSolicitud(s: Solicitud): Result<Solicitud>

    suspend fun updateSolicitud(id: Int, s: Solicitud): Result<Solicitud>

    suspend fun deleteSolicitud(id: Int): Result<Unit>
}