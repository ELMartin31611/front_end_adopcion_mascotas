package com.adopcion.di

import com.adopcion.data.repository.*
import com.adopcion.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindFundacionRepository(
        impl: FundacionRepositoryImpl
    ): FundacionRepository

    @Binds
    @Singleton
    abstract fun bindMascotaRepository(
        impl: MascotaRepositoryImpl
    ): MascotaRepository

    @Binds
    @Singleton
    abstract fun bindRescateRepository(
        impl: RescateRepositoryImpl
    ): RescateRepository

    @Binds
    @Singleton
    abstract fun bindSolicitudRepository(
        impl: SolicitudRepositoryImpl
    ): SolicitudRepository

    @Binds
    @Singleton
    abstract fun bindDonacionRepository(
        impl: DonacionRepositoryImpl
    ): DonacionRepository
}