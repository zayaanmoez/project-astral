package com.project.astral.data.models.launch

import com.google.gson.annotations.SerializedName

data class Agency(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("type") val type: String?,
    @field:SerializedName("country_code") val country_code: String?,
    @field:SerializedName("abbrev") val abbrev: String?,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("logo_url") val logo_url: String?,
    @field:SerializedName("info_url") val info_url: String?,
) {}