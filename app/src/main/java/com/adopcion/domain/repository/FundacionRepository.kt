package com.adopcion.domain.repository

import com.adopcion.domain.model.Fundacion
import com.adopcion.domain.model.FundacionPayload

interface FundacionRepository {

    suspend fun getFundaciones(): Result<List<Fundacion>>

    suspend fun getFundacion(id: Int): Result<Fundacion>

    suspend fun createFundacion(payload: FundacionPayload): Result<Fundacion>

    suspend fun updateFundacion(
        id: Int,
        payload: FundacionPayload
    ): Result<Fundacion>

    suspend fun deleteFundacion(id: Int): Result<Unit>
}