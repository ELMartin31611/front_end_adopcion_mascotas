package com.adopcion.data.remote.api

import com.adopcion.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface DonacionApi {

    @GET("donaciones/")
    suspend fun getDonaciones(): Response<PaginatedDto<DonacionDto>>

    @GET("donaciones/{id}/")
    suspend fun getDonacion(@Path("id") id: Int): Response<DonacionDto>

    @POST("donaciones/")
    suspend fun createDonacion(@Body body: DonacionRequestDto): Response<DonacionDto>

    @PATCH("donaciones/{id}/")
    suspend fun updateDonacion(
        @Path("id") id: Int,
        @Body body: DonacionRequestDto,
    ): Response<DonacionDto>

    @DELETE("donaciones/{id}/")
    suspend fun deleteDonacion(@Path("id") id: Int): Response<Unit>
}