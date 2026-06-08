package com.adopcion.data.remote.api

import com.adopcion.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface RescateApi {

    @GET("rescates/")
    suspend fun getRescates(): Response<PaginatedDto<RescateDto>>

    @GET("rescates/{id}/")
    suspend fun getRescate(@Path("id") id: Int): Response<RescateDto>

    @POST("rescates/")
    suspend fun createRescate(@Body body: RescateRequestDto): Response<RescateDto>

    @PATCH("rescates/{id}/")
    suspend fun updateRescate(
        @Path("id") id: Int,
        @Body body: RescateRequestDto,
    ): Response<RescateDto>

    @DELETE("rescates/{id}/")
    suspend fun deleteRescate(@Path("id") id: Int): Response<Unit>
}