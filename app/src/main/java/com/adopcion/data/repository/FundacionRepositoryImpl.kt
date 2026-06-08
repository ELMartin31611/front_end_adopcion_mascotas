package com.adopcion.data.repository

import com.adopcion.data.remote.api.FundacionApi
import com.adopcion.data.remote.dto.toDomain
import com.adopcion.data.remote.dto.toRequest
import com.adopcion.domain.model.Fundacion
import com.adopcion.domain.model.FundacionPayload
import com.adopcion.domain.repository.FundacionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FundacionRepositoryImpl @Inject constructor(
    private val api: FundacionApi,
) : FundacionRepository {

    override suspend fun getFundaciones(): Result<List<Fundacion>> = runCatching {
        val response = api.getFundaciones()

        if (response.isSuccessful) {
            response.body()!!.results.map { it.toDomain() }
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun getFundacion(id: Int): Result<Fundacion> = runCatching {
        val response = api.getFundacion(id)

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun createFundacion(payload: FundacionPayload): Result<Fundacion> =
        runCatching {
            val response = api.createFundacion(payload.toRequest())

            if (response.isSuccessful) {
                response.body()!!.toDomain()
            } else {
                error("Error ${response.code()}: ${response.errorBody()?.string()}")
            }
        }

    override suspend fun updateFundacion(
        id: Int,
        payload: FundacionPayload,
    ): Result<Fundacion> = runCatching {

        val response = api.updateFundacion(id, payload.toRequest())

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun deleteFundacion(id: Int): Result<Unit> = runCatching {
        val response = api.deleteFundacion(id)

        if (!response.isSuccessful) {
            error("Error ${response.code()}")
        }
    }
}