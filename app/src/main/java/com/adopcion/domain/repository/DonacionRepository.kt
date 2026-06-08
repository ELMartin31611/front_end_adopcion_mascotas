package com.adopcion.domain.repository

import com.adopcion.domain.model.Donacion

interface DonacionRepository {

    suspend fun getDonaciones(): Result<List<Donacion>>

    suspend fun getDonacion(id: Int): Result<Donacion>

    suspend fun createDonacion(d: Donacion): Result<Donacion>

    suspend fun updateDonacion(id: Int, d: Donacion): Result<Donacion>

    suspend fun deleteDonacion(id: Int): Result<Unit>
}