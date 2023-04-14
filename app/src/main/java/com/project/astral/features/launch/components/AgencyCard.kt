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
import com.project.astral.data.models.launch.Agency

@Composable
fun AgencyCard(agency: Agency, showWebViewer: (String) -> Unit) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier
            .width(400.dp)
            .wrapContentSize()
    ) {
        Column() {
            Spacer(modifier = Modifier.height(10.dp))
            AsyncImage(
                model = agency.logo_url,
                contentDescription= agency.name,
                Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 20.dp)
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
                    text = agency.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                )
                Spacer(modifier = Modifier.height(15.dp))
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type - ${agency.type}",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Country - ${agency.country_code}",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                agency.description?.let {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                    )
                }
                agency.info_url?.let {
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(onClick = { showWebViewer(it) }) {
                        Icon(
                            painterResource( id = R.drawable.baseline_info_24 ),
                            contentDescription = "More Info"
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = "MORE INFO", fontSize = 15.sp)
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}