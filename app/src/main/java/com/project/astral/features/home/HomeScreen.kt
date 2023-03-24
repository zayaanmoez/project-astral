package com.project.astral.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.project.astral.core.components.Text
import com.project.astral.core.utils.astralFont
import com.project.astral.viewmodels.HomeViewModel
import java.time.ZoneId
import java.time.ZonedDateTime

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val apodResource by homeViewModel.apodResource.observeAsState();

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        item {
            Text(
                text = greeting(),
                font = astralFont(),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 25.sp
            )
        }
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        // Astronomy Picture of the day
        apodResource?.let {
            item {
                Card(
                    modifier = Modifier.wrapContentSize()
                ) {
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                    ) {
                        Text(
                            text = it.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        AsyncImage(
                            model = it.hdurl,
                            contentDescription= it.title,
                            Modifier
                                .fillMaxWidth(1f)
                        )
                        Text(
                            text = "Credit & Copyright: ${it.copyright}",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = it.explanation,
                            fontSize = 17.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}

fun greeting(): String {
    return when (ZonedDateTime.now(ZoneId.systemDefault()).hour) {
        in 5..11 -> "Good Morning!"
        in 12..17 -> "Good Afternoon!"
        in 18..22 -> "Good Evening!"
        else -> "I know nothing with any certainty, but the sight of the stars makes me dream\n- Vicent Van Gogh"
    }
}