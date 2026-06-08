package com.adopcion.domain.repository

import com.adopcion.domain.model.Rescate

interface RescateRepository {

    suspend fun getRescates(): Result<List<Rescate>>

    suspend fun getRescate(id: Int): Result<Rescate>

    suspend fun createRescate(r: Rescate): Result<Rescate>

    suspend fun updateRescate(id: Int, r: Rescate): Result<Rescate>

    suspend fun deleteRescate(id: Int): Result<Unit>
}