package com.project.astral.data.models.spaceflight

import com.google.gson.annotations.SerializedName

data class SpaceflightArticle(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("url") val url : String,
    @field:SerializedName("imageUrl") val imageUrl: String,
    @field:SerializedName("newsSite") val newsSite: String,
    @field:SerializedName("summary") val summary: String,
    @field:SerializedName("publishedAt") val publishedAt: String,
    @field:SerializedName("launches") val launches : List<Launches>,
    @field:SerializedName("events") val events : List<Events>,
) {
    data class Launches(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("provider") val provider: Int,
    )

    data class Events(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("provider") val provider: Int,
    )
}