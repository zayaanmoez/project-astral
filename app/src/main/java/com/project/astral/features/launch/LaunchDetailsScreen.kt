package com.project.astral.features.launch

import com.project.astral.data.models.launch.Launch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.astral.core.components.WebViewer
import com.project.astral.features.launch.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailsScreen(launch: Launch, showDetails: (Boolean) -> Unit) {
    var showWebView by rememberSaveable { mutableStateOf(false) }
    var webViewUrl by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = launch.name,
                        fontSize = 15.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { showDetails(false) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                // Launch Item
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    LaunchItem(launch = launch, showDetails = {})
                }
                // Agency Info
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    AgencyCard(launch.agency)
                }
                // Mission Info
                launch.mission?.let {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                        MissionCard(it)
                    }
                }
                // Launch Pad Info
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    LaunchPadCard(launch.pad)
                }
                // Rocket Info
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    RocketCard(launch.rocket)
                }

                // Related News
                if (launch.articles.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    item {
                        RelatedNewsCard(articles = launch.articles, showWebViewer = { url ->
                            showWebView = true
                            webViewUrl = url
                        })
                    }
                }
            }
        }
    }

    // WebView to view articles
    if (showWebView) {
        WebViewer(url = webViewUrl, showViewer = {
            showWebView = it
        })
    }
}