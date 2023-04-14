package com.project.astral.features.launch.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.project.astral.common.components.Text
import com.project.astral.data.models.launch.Launch

@Composable
fun LaunchStatusChip(status: Launch.Status) {
    val successStates = listOf("Go", "Success")
    val holdStates = listOf("TBC", "TBD", "Hold")
    val failureStates = listOf("Failure", "Partial Failure")

    Button(
        onClick = {},
        enabled = false,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor =
            when (status.abbrev) {
                in successStates -> Color(0xff376e37)
                in holdStates -> Color(0xff8d4004)
                in failureStates -> Color(0xff8f0000)
                else -> Color(0xff0275dB)
            }
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(50))
    ) {
        Text(
            text = status.abbrev,
            fontSize = 15.sp
        )
    }
}