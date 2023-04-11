package com.project.astral.data.models.launch

import com.google.gson.annotations.SerializedName
import com.project.astral.data.models.Article
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class Launch(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("slug") val slug: String,
    @field:SerializedName("status") val status: Status,
    @field:SerializedName("net") val net: String?,
    @field:SerializedName("window_end") val window_end: String?,
    @field:SerializedName("window_start") val window_start: String?,
    @field:SerializedName("holdReason") val holdReason: String?,
    @field:SerializedName("failReason") val failReason: String?,
    @field:SerializedName("image") val image: String,
    @field:SerializedName("launch_service_provider") val agency: Agency,
    @field:SerializedName("rocket") val rocket: Rocket,
    @field:SerializedName("mission") val mission: Mission?,
    @field:SerializedName("pad") val pad: Pad,
    @field:SerializedName("infoURLs") val infoURLs: List<URL>,
    @field:SerializedName("vidURLs") val videURLs: List<URL>,
    var articles: List<Article>,
) {
    data class Status(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("abbrev") val abbrev: String,
        @field:SerializedName("description") val description: String,
    )

    data class URL (
        @field:SerializedName("priority") val id: String,
        @field:SerializedName("url") val url: String,
    )

    enum class Type {
        UPCOMING, PAST
    }

    fun getLaunchDateTime(): String {
        val odtPublished = OffsetDateTime.parse(net)

        // Formatted date
        return odtPublished.atZoneSameInstant(ZoneId.systemDefault()).format(
            DateTimeFormatter.ofPattern("MM/dd/y, hh:mm:ss a")
        )
    }
}