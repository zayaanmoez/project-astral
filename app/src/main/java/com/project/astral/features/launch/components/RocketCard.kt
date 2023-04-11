package com.project.astral.features.launch.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.astral.data.models.launch.Rocket

@Composable
fun RocketCard(rocket: Rocket) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .width(400.dp)
            .wrapContentSize()
    ) {
        Column() {
            AsyncImage(
                model = rocket.config.image_url,
                contentDescription= rocket.config.name,
                Modifier
                    .fillMaxWidth(1f)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = rocket.config.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}