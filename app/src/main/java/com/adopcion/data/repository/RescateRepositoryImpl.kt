package com.adopcion.data.repository

import com.adopcion.data.remote.api.RescateApi
import com.adopcion.data.remote.dto.toDomain
import com.adopcion.data.remote.dto.toRequest
import com.adopcion.domain.model.Rescate
import com.adopcion.domain.model.RescatePayload
import com.adopcion.domain.repository.RescateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RescateRepositoryImpl @Inject constructor(
    private val api: RescateApi,
) : RescateRepository {

    override suspend fun getRescates(): Result<List<Rescate>> = runCatching {
        val response = api.getRescates()

        if (response.isSuccessful) {
            response.body()?.results?.map { it.toDomain() } ?: emptyList()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun getRescate(id: Int): Result<Rescate> = runCatching {
        val response = api.getRescate(id)

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun createRescate(
        payload: RescatePayload
    ): Result<Rescate> = runCatching {

        val response = api.createRescate(payload.toRequest())

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun updateRescate(
        id: Int,
        payload: RescatePayload
    ): Result<Rescate> = runCatching {

        val response = api.updateRescate(
            id,
            payload.toRequest()
        )

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun deleteRescate(id: Int): Result<Unit> = runCatching {
        val response = api.deleteRescate(id)

        if (!response.isSuccessful) {
            error("Error ${response.code()}")
        }
    }
}