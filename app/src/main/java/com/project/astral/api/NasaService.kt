package com.project.astral.api

import com.project.astral.BuildConfig
import com.project.astral.data.models.apod.ApodResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {

    @GET("planetary/apod")
    suspend fun getAPOD(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ) : ApodResponse

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"

        fun create(): NasaService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NasaService::class.java)
        }
    }
}