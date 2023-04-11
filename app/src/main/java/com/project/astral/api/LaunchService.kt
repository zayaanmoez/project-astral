package com.project.astral.api

import com.project.astral.data.models.launch.LaunchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface LaunchService {

    @GET("launch/previous/")
    suspend fun getPastLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("mode") mode: String = "detailed"
    ) : LaunchResponse

    @GET("launch/upcoming/")
    suspend fun getUpcomingLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("mode") mode: String = "detailed"
    ) : LaunchResponse

    companion object {
        private const val LAUNCH_DEV = "https://lldev.thespacedevs.com/2.2.0/"
        private const val LAUNCH_V2 = "https://ll.thespacedevs.com/2.2.0/"
        private const val BASE_URL = LAUNCH_DEV

        fun create(): LaunchService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LaunchService::class.java)
        }
    }
}