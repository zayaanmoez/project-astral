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
import coil.compose.AsyncImage
import com.project.astral.R
import com.project.astral.data.models.launch.Rocket

@Composable
fun RocketCard(rocket: Rocket, showWebViewer: (String) -> Unit) {
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

                Spacer(modifier = Modifier.height(15.dp))
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(12.dp))

                // Rocket Specifications
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource( id = R.drawable.baseline_list_alt_24 ),
                        contentDescription = "Specifications"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( text = "Specifications", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(12.dp))
                ConfigAttribute("Max Stage", rocket.config.max_stage)
                ConfigAttribute("Length", rocket.config.length, "Meters")
                ConfigAttribute("Diameter", rocket.config.diameter, "Meters")
                ConfigAttribute("Liftoff Mass", rocket.config.launch_mass, "Tonnes")
                ConfigAttribute("Takeoff Thrust", rocket.config.to_thrust, "kN")

                Spacer(modifier = Modifier.height(12.dp))
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(12.dp))

                // Payload info
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource( id = R.drawable.baseline_scale_24 ),
                        contentDescription = "Payload Capacity"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( text = "Payload Capacity", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(12.dp))
                ConfigAttribute("Launch Cost", rocket.config.launch_cost, unitPrefix = "$")
                ConfigAttribute("Low Earth Orbit", rocket.config.leo_capacity, "kg")
                ConfigAttribute("Geostationary Transfer Orbit", rocket.config.gto_capacity, "kg")

                Spacer(modifier = Modifier.height(12.dp))
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(12.dp))

                // Payload info
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painterResource( id = R.drawable.baseline_rocket_launch_24 ),
                        contentDescription = "Launch Stats"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text( text = "Launch Stats", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(12.dp))
                ConfigAttribute("Launch Success", rocket.config.successful_launches)
                ConfigAttribute("Consecutive Success", rocket.config.consecutive_launches)
                ConfigAttribute("Launch Failure", rocket.config.failed_launches)
                ConfigAttribute("Maiden Flight", rocket.config.maiden_flight)

                Spacer(modifier = Modifier.height(12.dp))
                Divider(modifier = Modifier.padding(horizontal = 20.dp))

                Spacer(modifier = Modifier.height(15.dp))
                Row() {
                    rocket.config.info_url?.let {
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
                    rocket.config.wiki_url?.let {
                        Button(onClick = { showWebViewer(it) }) {
                            Icon(
                                painterResource( id = R.drawable.baseline_library_books_24 ),
                                contentDescription = "Wiki"
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = "WIKI", fontSize = 15.sp)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun ConfigAttribute(name: String, value: Any?, unitSuffix: String = "", unitPrefix: String = "") {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 5.dp)
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.Start)
        )
        Text(
            text = if(value != null) { "$unitPrefix $value $unitSuffix" } else { "-" },
            fontSize = 18.sp,
            textAlign = TextAlign.Right,
            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.End)
        )
    }
}