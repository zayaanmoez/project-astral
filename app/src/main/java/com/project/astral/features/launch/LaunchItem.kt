package com.project.astral.features.launch

import com.project.astral.data.models.launch.Launch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.astral.R
import com.project.astral.common.components.Text
import com.project.astral.features.launch.components.LaunchStatusChip

@Composable
fun LaunchItem(
    launch: Launch,
    showDetails: (Launch) -> Unit,
    showWebViewer: (String) -> Unit,
    showVideoViewer: (String) -> Unit
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
          containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .width(400.dp)
            .wrapContentSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = {
                    showDetails(launch)
                }
            ),
    ) {
        Column() {
            AsyncImage(
                model = launch.image,
                contentDescription= launch.name,
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
                    text = launch.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                LaunchStatusChip(launch.status)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = launch.agency.name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
                launch.pad.location?.name?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Text(
                    text = launch.getLaunchDateTime(),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (launch.videURLs.isNotEmpty()) {
                        Button(onClick = { showVideoViewer(launch.videURLs[0].url) }) {
                            Icon(
                                painterResource( id = R.drawable.baseline_play_circle_24 ),
                                contentDescription = "Play Video"
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "WATCH", fontSize = 15.sp)
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                    if (launch.infoURLs.isNotEmpty()) {
                        Button(onClick = { showWebViewer(launch.infoURLs[0].url) }) {
                            Icon(
                                painterResource( id = R.drawable.baseline_info_24 ),
                                contentDescription = "More Info"
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "MORE INFO", fontSize = 15.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}