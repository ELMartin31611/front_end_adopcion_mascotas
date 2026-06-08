package com.adopcion.domain.repository

import com.adopcion.domain.model.Mascota
import com.adopcion.domain.model.MascotaPayload

interface MascotaRepository {

    suspend fun getMascotas(): Result<List<Mascota>>

    suspend fun getMascota(id: Int): Result<Mascota>

    suspend fun createMascota(payload: MascotaPayload): Result<Mascota>

    suspend fun updateMascota(
        id: Int,
        payload: MascotaPayload
    ): Result<Mascota>

    suspend fun deleteMascota(id: Int): Result<Unit>
}