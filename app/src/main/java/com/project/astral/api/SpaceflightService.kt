package com.project.astral.api

import com.project.astral.data.models.spaceflight.SpaceflightArticle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceflightService {

    @GET("articles")
    suspend fun getArticles(
        @Query("_limit") limit: Int,
        @Query("_start") start: Int,
    ) : List<SpaceflightArticle>

    companion object {
        private const val BASE_URL = "https://api.spaceflightnewsapi.net/v3/"

        fun create(): SpaceflightService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SpaceflightService::class.java)
        }
    }
}