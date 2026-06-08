package com.adopcion.domain.repository

import com.adopcion.domain.model.Rescate
import com.adopcion.domain.model.RescatePayload

interface RescateRepository {

    suspend fun getRescates(): Result<List<Rescate>>

    suspend fun getRescate(id: Int): Result<Rescate>

    suspend fun createRescate(payload: RescatePayload): Result<Rescate>

    suspend fun updateRescate(
        id: Int,
        payload: RescatePayload
    ): Result<Rescate>

    suspend fun deleteRescate(id: Int): Result<Unit>
}