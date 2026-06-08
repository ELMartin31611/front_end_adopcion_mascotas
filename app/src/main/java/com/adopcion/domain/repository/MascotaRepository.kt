package com.adopcion.domain.repository

import com.adopcion.domain.model.Mascota

interface MascotaRepository {

    suspend fun getMascotas(): Result<List<Mascota>>

    suspend fun getMascota(id: Int): Result<Mascota>

    suspend fun createMascota(m: Mascota): Result<Mascota>

    suspend fun updateMascota(id: Int, m: Mascota): Result<Mascota>

    suspend fun deleteMascota(id: Int): Result<Unit>
}