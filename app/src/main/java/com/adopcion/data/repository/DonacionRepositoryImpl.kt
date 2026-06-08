package com.adopcion.data.repository

import com.adopcion.data.remote.api.DonacionApi
import com.adopcion.data.remote.dto.toDomain
import com.adopcion.data.remote.dto.toRequest
import com.adopcion.domain.model.Donacion
import com.adopcion.domain.model.DonacionPayload
import com.adopcion.domain.repository.DonacionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DonacionRepositoryImpl @Inject constructor(
    private val api: DonacionApi,
) : DonacionRepository {

    override suspend fun getDonaciones(): Result<List<Donacion>> = runCatching {
        val response = api.getDonaciones()

        if (response.isSuccessful) {
            response.body()?.results?.map { it.toDomain() } ?: emptyList()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun getDonacion(id: Int): Result<Donacion> = runCatching {
        val response = api.getDonacion(id)

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}")
        }
    }

    override suspend fun createDonacion(
        payload: DonacionPayload
    ): Result<Donacion> = runCatching {

        val response = api.createDonacion(
            payload.toRequest()
        )

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun updateDonacion(
        id: Int,
        payload: DonacionPayload
    ): Result<Donacion> = runCatching {

        val response = api.updateDonacion(
            id,
            payload.toRequest()
        )

        if (response.isSuccessful) {
            response.body()!!.toDomain()
        } else {
            error("Error ${response.code()}: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun deleteDonacion(id: Int): Result<Unit> = runCatching {
        val response = api.deleteDonacion(id)

        if (!response.isSuccessful) {
            error("Error ${response.code()}")
        }
    }
}