package com.project.astral.features.launch

import com.project.astral.data.models.launch.Launch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.astral.R
import com.project.astral.common.components.VideoViewer
import com.project.astral.common.components.WebViewer
import com.project.astral.features.launch.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchDetailsScreen(launch: Launch, showDetails: (Boolean) -> Unit) {
    var showWebView by rememberSaveable { mutableStateOf(false) }
    var webViewUrl by rememberSaveable { mutableStateOf("") }

    var showVideoView by rememberSaveable { mutableStateOf(false) }
    var videoUrl by rememberSaveable { mutableStateOf("") }

    // Tabs for launch details
    var state by rememberSaveable { mutableStateOf(0) }
    val detailTabs = if(launch.mission != null) {
        listOf<String>("Agency", "Mission", "Pad", "Rocket")
    } else {
        listOf<String>("Agency", "Pad", "Rocket")
    }

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
                    LaunchItem(
                        launch = launch,
                        showDetails = {},
                        showWebViewer = { url ->
                            showWebView = true
                            webViewUrl = url
                        },
                        showVideoViewer = { url ->
                            showVideoView = true
                            videoUrl = url
                        }
                    )
                }

                //  Detail tabs
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
                item {
                    TabRow(
                        selectedTabIndex = state,
                        Modifier.clip(RoundedCornerShape(50))

                    ) {
                        detailTabs.forEachIndexed { index, tab ->
                            Tab(
                                selected = state == index,
                                onClick = { state = index},
                                text = {
                                    Text(
                                        text = tab,
                                        fontSize = 15.sp
                                    )
                                },
                                modifier = Modifier.clip(RoundedCornerShape(50))
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }

                if (detailTabs[state] == "Agency") {
                    // Agency Info
                    item {
                        AgencyCard(
                            launch.agency,
                            showWebViewer = { url ->
                                showWebView = true
                                webViewUrl = url
                            })
                    }
                } else if (detailTabs[state] == "Mission") {
                    // Mission Info
                    launch.mission?.let {
                        item {
                            MissionCard(it)
                        }
                    }
                } else if (detailTabs[state] == "Pad") {
                    // Launch Pad Info
                    item {
                        LaunchPadCard(
                            launch.pad,
                            showWebViewer = { url ->
                                showWebView = true
                                webViewUrl = url
                            }
                        )
                    }
                } else {
                    // Rocket Info
                    item {
                        RocketCard(
                            launch.rocket,
                            showWebViewer = { url ->
                                showWebView = true
                                webViewUrl = url
                            }
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }

                // Related News
                if (launch.articles.isNotEmpty()) {
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

    // Video View to play videos
    if (showVideoView) {
        VideoViewer(url = videoUrl, showViewer = {
            showVideoView = it
        })
    }
}