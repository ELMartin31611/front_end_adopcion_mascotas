package com.adopcion.data.repository

import com.adopcion.data.remote.api.MascotaApi
import com.adopcion.data.remote.dto.toDomain
import com.adopcion.data.remote.dto.toRequest
import com.adopcion.domain.model.Mascota
import com.adopcion.domain.model.MascotaPayload
import com.adopcion.domain.repository.MascotaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MascotaRepositoryImpl @Inject constructor(
    private val api: MascotaApi,
) : MascotaRepository {

    override suspend fun getMascotas(): Result<List<Mascota>> =
        runCatching {

            val response = api.getMascotas(emptyMap())

            if (response.isSuccessful) {
                response.body()!!.results.map { it.toDomain() }
            } else {
                error("Error ${response.code()}")
            }
        }

    override suspend fun getMascota(id: Int): Result<Mascota> =
        runCatching {

            val response = api.getMascota(id)

            if (response.isSuccessful) {
                response.body()!!.toDomain()
            } else {
                error("Error ${response.code()}")
            }
        }

    override suspend fun createMascota(
        payload: MascotaPayload
    ): Result<Mascota> =
        runCatching {

            val response = api.createMascota(
                payload.toRequest()
            )

            if (response.isSuccessful) {
                response.body()!!.toDomain()
            } else {
                error(
                    "Error ${response.code()}: ${
                        response.errorBody()?.string()
                    }"
                )
            }
        }

    override suspend fun updateMascota(
        id: Int,
        payload: MascotaPayload,
    ): Result<Mascota> =
        runCatching {

            val response = api.updateMascota(
                id,
                payload.toRequest()
            )

            if (response.isSuccessful) {
                response.body()!!.toDomain()
            } else {
                error(
                    "Error ${response.code()}: ${
                        response.errorBody()?.string()
                    }"
                )
            }
        }

    override suspend fun deleteMascota(
        id: Int
    ): Result<Unit> =
        runCatching {

            val response = api.deleteMascota(id)

            if (!response.isSuccessful) {
                error("Error ${response.code()}")
            }
        }
}