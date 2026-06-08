package com.adopcion.domain.repository

import com.adopcion.domain.model.Donacion
import com.adopcion.domain.model.DonacionPayload

interface DonacionRepository {

    suspend fun getDonaciones(): Result<List<Donacion>>

    suspend fun getDonacion(id: Int): Result<Donacion>

    suspend fun createDonacion(payload: DonacionPayload): Result<Donacion>

    suspend fun updateDonacion(
        id: Int,
        payload: DonacionPayload
    ): Result<Donacion>

    suspend fun deleteDonacion(id: Int): Result<Unit>
}