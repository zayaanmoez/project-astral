package com.project.astral.data.models.apod

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    @field:SerializedName("copyright") val copyright: String,
    @field:SerializedName("date") val date: String,
    @field:SerializedName("explanation") val explanation: String,
    @field:SerializedName("hdurl") val hdurl: String,
    @field:SerializedName("media_type") val media_type: String,
    @field:SerializedName("service_version") val service_version: String,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("url") val url: String
) {
}