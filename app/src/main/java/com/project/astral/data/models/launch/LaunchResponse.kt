package com.project.astral.data.models.launch

import com.google.gson.annotations.SerializedName

data class LaunchResponse(
    @field:SerializedName("count") val count: Int,
    @field:SerializedName("next") val next: String?,
    @field:SerializedName("previous") val previous: String?,
    @field:SerializedName("results") val results: List<Launch>
) {}