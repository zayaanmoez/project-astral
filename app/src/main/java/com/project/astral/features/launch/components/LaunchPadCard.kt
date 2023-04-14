package com.project.astral.features.launch.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.project.astral.R
import com.project.astral.data.models.launch.Pad

@Composable
fun LaunchPadCard(pad: Pad, showWebViewer: (String) -> Unit) {

    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .width(400.dp)
            .wrapContentSize()
    ) {
        Column() {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
            ) {
                pad.name?.let {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = it,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                pad.location?.name?.let {
                    Divider(modifier = Modifier.padding(horizontal = 20.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Location - ${pad.location.name}",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                Row() {
                    pad.info_url?.let {
                        Button(onClick = { showWebViewer(it) }) {
                            Icon(
                                painterResource( id = R.drawable.baseline_info_24 ),
                                contentDescription = "More Info"
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "MORE INFO", fontSize = 15.sp)
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                    pad.wiki_url?.let {
                        Button(onClick = { showWebViewer(it) }) {
                            Icon(
                                painterResource( id = R.drawable.baseline_library_books_24 ),
                                contentDescription = "Wiki"
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "WIKI", fontSize = 15.sp)
                        }
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                    pad.map_url?.let {
                        Button(onClick = { showWebViewer(it) }) {
                            Icon(
                                painterResource( id = R.drawable.baseline_map_24 ),
                                contentDescription = "Google maps"
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "MAP", fontSize = 15.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))

            }
            if (pad.latitude?.isNotEmpty() == true && pad.longitude?.isNotEmpty() == true) {
                val launchPad = LatLng(pad.latitude.toDouble(), pad.longitude.toDouble())
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(launchPad, 10f)
                }
                GoogleMap(
                    modifier = Modifier.fillMaxSize().height(500.dp),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = launchPad),
                        title = pad.name,
                        snippet = "Launch site"
                    )
                }
            }
        }
    }
}