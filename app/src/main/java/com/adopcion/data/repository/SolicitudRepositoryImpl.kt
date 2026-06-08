package com.adopcion.data.repository

import com.adopcion.data.remote.api.SolicitudApi
import com.adopcion.data.remote.dto.toDomain
import com.adopcion.data.remote.dto.toRequest
import com.adopcion.domain.model.Solicitud
import com.adopcion.domain.model.SolicitudPayload
import com.adopcion.domain.repository.SolicitudRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SolicitudRepositoryImpl @Inject constructor(
    private val api: SolicitudApi,
) : SolicitudRepository {

    override suspend fun getSolicitudes(): Result<List<Solicitud>> = runCatching {
        val response = api.getSolicitudes()

        if (response.isSuccessful) {
            response.body()?.results?.map { it.toDomain() } ?: emptyList()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun getSolicitud(id: Int): Result<Solicitud> = runCatching {
        val response = api.getSolicitud(id)

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun createSolicitud(
        payload: SolicitudPayload
    ): Result<Solicitud> = runCatching {

        val response = api.createSolicitud(
            payload.toRequest()
        )

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun updateSolicitud(
        id: Int,
        payload: SolicitudPayload
    ): Result<Solicitud> = runCatching {

        val response = api.updateSolicitud(
            id,
            payload.toRequest()
        )

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun deleteSolicitud(id: Int): Result<Unit> = runCatching {
        val response = api.deleteSolicitud(id)

        if (!response.isSuccessful) {
            error("Error ${response.code()}")
        }
    }
}