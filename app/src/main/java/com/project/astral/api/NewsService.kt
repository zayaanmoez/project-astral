package com.project.astral.api

import com.project.astral.BuildConfig
import com.project.astral.data.models.newsapi.NewsApiResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines?category=science")
    suspend fun getArticles(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("category") category: String = "science",
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = BuildConfig.NEWS_API_KEY
    ) : NewsApiResponse

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"

        fun create(): NewsService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsService::class.java)
        }
    }
}