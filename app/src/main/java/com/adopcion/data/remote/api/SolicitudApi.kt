package com.adopcion.data.remote.api

import com.adopcion.data.remote.dto.PaginatedDto
import com.adopcion.data.remote.dto.SolicitudDto
import com.adopcion.data.remote.dto.SolicitudRequestDto
import retrofit2.Response
import retrofit2.http.*

interface SolicitudApi {

    @GET("solicitudes/")
    suspend fun getSolicitudes(): Response<PaginatedDto<SolicitudDto>>

    @GET("solicitudes/{id}/")
    suspend fun getSolicitud(
        @Path("id") id: Int
    ): Response<SolicitudDto>

    @POST("solicitudes/")
    suspend fun createSolicitud(
        @Body body: SolicitudRequestDto
    ): Response<SolicitudDto>

    @PATCH("solicitudes/{id}/")
    suspend fun updateSolicitud(
        @Path("id") id: Int,
        @Body body: SolicitudRequestDto
    ): Response<SolicitudDto>

    @DELETE("solicitudes/{id}/")
    suspend fun deleteSolicitud(
        @Path("id") id: Int
    ): Response<Unit>
}