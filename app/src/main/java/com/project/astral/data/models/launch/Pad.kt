package com.project.astral.data.models.launch

import com.google.gson.annotations.SerializedName

data class Pad(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("map_url") val map_url: String?,
    @field:SerializedName("latitude") val latitude: String?,
    @field:SerializedName("longitude") val longitude: String?,
    @field:SerializedName("location") val location: Location,
) {
    data class Location(
        @field:SerializedName("name") val name: String?,
        @field:SerializedName("country_code") val country_code: String?,
    )
}