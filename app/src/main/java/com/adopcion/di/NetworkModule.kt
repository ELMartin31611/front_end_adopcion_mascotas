package com.adopcion.di

import com.adopcion.BuildConfig
import com.adopcion.data.local.TokenDataStore
import com.adopcion.data.remote.api.*
import com.adopcion.data.remote.interceptor.AuthInterceptor
import com.adopcion.data.remote.interceptor.BearerTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenDataStore: TokenDataStore,
        authInterceptor: AuthInterceptor,
        logging: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .authenticator(authInterceptor)
        .addInterceptor(BearerTokenInterceptor(tokenDataStore))
        .addInterceptor(logging)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 🔐 AUTH
    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)

    // 🏢 FUNDACIONES
    @Provides
    @Singleton
    fun provideFundacionApi(retrofit: Retrofit): FundacionApi =
        retrofit.create(FundacionApi::class.java)

    // 🐶 MASCOTAS
    @Provides
    @Singleton
    fun provideMascotaApi(retrofit: Retrofit): MascotaApi =
        retrofit.create(MascotaApi::class.java)

    // 📄 SOLICITUDES
    @Provides
    @Singleton
    fun provideSolicitudApi(retrofit: Retrofit): SolicitudApi =
        retrofit.create(SolicitudApi::class.java)

    // 💰 DONACIONES
    @Provides
    @Singleton
    fun provideDonacionApi(retrofit: Retrofit): DonacionApi =
        retrofit.create(DonacionApi::class.java)

    // 🚑 RESCATES
    @Provides
    @Singleton
    fun provideRescateApi(retrofit: Retrofit): RescateApi =
        retrofit.create(RescateApi::class.java)
}