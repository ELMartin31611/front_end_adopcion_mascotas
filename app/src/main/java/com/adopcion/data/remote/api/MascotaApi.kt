package com.adopcion.data.remote.api

import com.adopcion.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface MascotaApi {

    @GET("mascotas/")
    suspend fun getMascotas(
        @QueryMap filters: Map<String, String>
    ): Response<PaginatedDto<MascotaDto>>

    @GET("mascotas/{id}/")
    suspend fun getMascota(@Path("id") id: Int): Response<MascotaDto>

    @POST("mascotas/")
    suspend fun createMascota(@Body body: MascotaRequestDto): Response<MascotaDto>

    @PATCH("mascotas/{id}/")
    suspend fun updateMascota(
        @Path("id") id: Int,
        @Body body: MascotaRequestDto,
    ): Response<MascotaDto>

    @DELETE("mascotas/{id}/")
    suspend fun deleteMascota(@Path("id") id: Int): Response<Unit>
}