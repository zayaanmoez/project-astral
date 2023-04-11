package com.project.astral.data.models.launch

import com.google.gson.annotations.SerializedName

data class Mission(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("description") val description: String?,
    @field:SerializedName("type") val type: String,
    @field:SerializedName("orbit") val orbit: Orbit,
) {
    data class Orbit(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("abbrev") val abbrev: String,
    )
}