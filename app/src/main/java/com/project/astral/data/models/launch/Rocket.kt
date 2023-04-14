package com.project.astral.data.models.launch

import com.google.gson.annotations.SerializedName

data class Rocket (
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("configuration") val config: Configuration,
    @field:SerializedName("launcher_stage") val launcher_stage: List<LauncherStage>,
) {
    data class Configuration(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("url") val url: String,
        @field:SerializedName("name") val name: String,
        @field:SerializedName("active") val active: Boolean,
        @field:SerializedName("reusable") val reusable: String,
        @field:SerializedName("family") val family: String,
        @field:SerializedName("full_name") val full_name: String,
        @field:SerializedName("variant") val variant: String,
        @field:SerializedName("alias") val alias: String,
        @field:SerializedName("min_stage") val min_stage: Int?,
        @field:SerializedName("max_stage") val max_stage: Int?,
        @field:SerializedName("length") val length: Float?,
        @field:SerializedName("diameter") val diameter: Float?,
        @field:SerializedName("maiden_flight") val maiden_flight: String?,
        @field:SerializedName("launch_cost") val launch_cost: String?,
        @field:SerializedName("launch_mass") val launch_mass: Int?,
        @field:SerializedName("leo_capacity") val leo_capacity: Int?,
        @field:SerializedName("gto_capacity") val gto_capacity: Int?,
        @field:SerializedName("to_thrust") val to_thrust: Int?,
        @field:SerializedName("image_url") val image_url: String?,
        @field:SerializedName("info_url") val info_url: String?,
        @field:SerializedName("wiki_url") val wiki_url: String?,
        @field:SerializedName("successful_launches") val successful_launches: Int?,
        @field:SerializedName("consecutive_successful_launches") val consecutive_launches: Int?,
        @field:SerializedName("failed_launches") val failed_launches: Int?,
        @field:SerializedName("pending_launches") val pending_launches: Int?,
    )

    data class LauncherStage(
        @field:SerializedName("type") val type: String,
        @field:SerializedName("reused") val reused: Boolean,
        @field:SerializedName("launcher") val launcher: Launcher,
        @field:SerializedName("landing") val landing: Landing?,
    )

    data class Launcher(
        @field:SerializedName("details") val details: String,
        @field:SerializedName("flight_proven") val flight_proven: Boolean,
        @field:SerializedName("image_url") val image_url: String?,
        @field:SerializedName("serial_number") val serial_number: String?,
        @field:SerializedName("status") val status: String,
        @field:SerializedName("flights") val flights: Int?,
    )

    data class Landing(
        @field:SerializedName("attempt") val attempt: Boolean?,
        @field:SerializedName("success") val success: Boolean?,
        @field:SerializedName("description") val description: String,
        @field:SerializedName("location") val location: Location?,
        @field:SerializedName("type") val type: Type?,
    ) {
        data class Location(
            @field:SerializedName("name") val name: String?,
            @field:SerializedName("description") val description: String?,
            @field:SerializedName("abbrev") val abbrev: String?,
        )

        data class Type(
            @field:SerializedName("name") val name: String?,
            @field:SerializedName("description") val description: String?,
            @field:SerializedName("abbrev") val abbrev: String?,
        )

    }
}