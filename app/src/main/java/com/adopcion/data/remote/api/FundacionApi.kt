package com.adopcion.data.remote.api

import com.adopcion.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface FundacionApi {

    @GET("fundaciones/")
    suspend fun getFundaciones(): Response<PaginatedDto<FundacionDto>>

    @GET("fundaciones/{id}/")
    suspend fun getFundacion(@Path("id") id: Int): Response<FundacionDto>

    @POST("fundaciones/")
    suspend fun createFundacion(@Body body: FundacionRequestDto): Response<FundacionDto>

    @PATCH("fundaciones/{id}/")
    suspend fun updateFundacion(
        @Path("id") id: Int,
        @Body body: FundacionRequestDto,
    ): Response<FundacionDto>

    @DELETE("fundaciones/{id}/")
    suspend fun deleteFundacion(@Path("id") id: Int): Response<Unit>

    @GET("fundaciones/stats/")
    suspend fun getStats(): Response<Map<String, Any>>
}