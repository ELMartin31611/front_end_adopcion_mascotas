package com.adopcion.data.remote.interceptor

import com.adopcion.BuildConfig
import com.adopcion.data.local.TokenDataStore
import com.adopcion.data.remote.dto.TokenRefreshRequest
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 🔐 Authenticator:
 * Se ejecuta automáticamente cuando el token expira (401)
 * Intenta refrescar el token usando el refresh token
 */
@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        // Evita reintentos infinitos
        if (response.request.header("X-Retry") != null) return null

        val refreshToken = runBlocking {
            tokenDataStore.getRefreshToken()
        } ?: return null

        val client = OkHttpClient()
        val gson = Gson()

        val requestBody = gson.toJson(
            TokenRefreshRequest(refreshToken)
        )

        val refreshRequest = Request.Builder()
            .url("${BuildConfig.API_BASE_URL}auth/token/refresh/")
            .post(requestBody.toRequestBody("application/json".toMediaType()))
            .build()

        val refreshResponse = try {
            client.newCall(refreshRequest).execute()
        } catch (e: Exception) {
            return null
        }

        if (!refreshResponse.isSuccessful) {
            runBlocking { tokenDataStore.clearSession() }
            return null
        }

        val responseBody = refreshResponse.body?.string() ?: return null

        val newAccessToken = try {
            gson.fromJson(responseBody, Map::class.java)["access"] as? String
        } catch (e: Exception) {
            null
        } ?: return null

        runBlocking {
            tokenDataStore.saveAccessToken(newAccessToken)
        }

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .header("X-Retry", "true")
            .build()
    }
}

/**
 * 🔐 Interceptor:
 * Añade el token Bearer a TODAS las requests automáticamente
 */
class BearerTokenInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val accessToken = runBlocking {
            tokenDataStore.getAccessToken()
        }

        val request = if (accessToken != null) {
            chain.request().newBuilder()
                .header("Authorization", "Bearer $accessToken")
                .build()
        } else {
            chain.request()
        }

        return chain.proceed(request)
    }
}