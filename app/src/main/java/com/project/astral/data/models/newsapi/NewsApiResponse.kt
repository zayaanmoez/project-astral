package com.project.astral.data.models.newsapi

import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("totalResults") val totalResults: Int,
    @field:SerializedName("articles") val articles: List<NewsApiArticle>
) {
    data class NewsApiArticle(
        @field:SerializedName("title") val title: String,
        @field:SerializedName("url") val url : String,
        @field:SerializedName("urlToImage") val urlToImage: String?,
        @field:SerializedName("source") val source: Source,
        @field:SerializedName("description") val description: String?,
        @field:SerializedName("publishedAt") val publishedAt: String,
    ) {
        data class Source(
            @field:SerializedName("name") val name: String,
        )
    }
}