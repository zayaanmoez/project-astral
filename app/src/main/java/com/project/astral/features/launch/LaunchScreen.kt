package com.project.astral.features.launch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.astral.common.components.Text
import com.project.astral.common.components.VideoViewer
import com.project.astral.common.components.WebViewer
import com.project.astral.common.utils.astralFont
import com.project.astral.common.utils.isScrolledToTheEnd
import com.project.astral.data.models.launch.Launch
import com.project.astral.viewmodels.LaunchViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LaunchScreen(launchViewModel: LaunchViewModel = hiltViewModel()) {
    val pastLaunches by launchViewModel.pastLaunchList.observeAsState()
    val upcomingLaunches by launchViewModel.upcomingLaunchList.observeAsState()
    val scrollState = rememberLazyListState()

    // Web viewer state
    var showWebView by rememberSaveable { mutableStateOf(false) }
    var webViewUrl by rememberSaveable { mutableStateOf("") }

    // Video Viewer state
    var showVideoView by rememberSaveable { mutableStateOf(false) }
    var videoUrl by rememberSaveable { mutableStateOf("") }

    // Tabs for past/upcoming launches
    var state by rememberSaveable { mutableStateOf(0) }
    val launchTypes = Launch.Type.values()

    // Launch details Screen state
    var launchId: String? by rememberSaveable { mutableStateOf(null) }
    var showDetailsView by rememberSaveable { mutableStateOf(false) }

    // Reload data on pull refresh
    val refreshScope = rememberCoroutineScope()
    var refreshing by rememberSaveable { mutableStateOf(false) }
    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            refreshScope.launch {
                refreshing = true
                launchViewModel.reloadLaunches(launchTypes[state])
                delay(1000)
                refreshing = false
            }
        }
    )

    // observer when reached end of list to load more launches
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToTheEnd()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .pullRefresh(refreshState)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState
        ) {
            item {
                Text(
                    text = "Launches",
                    font = astralFont(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(10.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                TabRow(
                    selectedTabIndex = state,
                    Modifier.clip(RoundedCornerShape(50))

                ) {
                    launchTypes.forEachIndexed { index, type ->
                        Tab(
                            selected = state == index,
                            onClick = { state = index},
                            text = { Text(text = type.toString(), fontSize = 15.sp) },
                            modifier = Modifier.clip(RoundedCornerShape(50))
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
            if (launchTypes[state] == Launch.Type.UPCOMING) {
                upcomingLaunches
            } else {
                pastLaunches
            }?.let { it ->
                if (it.isEmpty()) {
                    item {
                        CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                    }
                } else {
                    items(it) {launch ->
                        LaunchItem(
                            launch = launch,
                            showDetails = { launch ->
                                showDetailsView = true
                                launchId = launch.id
                            },
                            showWebViewer = { url ->
                                showWebView = true
                                webViewUrl = url
                            },
                            showVideoViewer = { url ->
                                showVideoView = true
                                videoUrl = url
                            }
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Divider(modifier = Modifier.padding(horizontal = 20.dp))
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
                if (it.isNotEmpty()) {
                    item {
                        // Load more launches if end of list is reached
                        LaunchedEffect(endOfListReached) {
                            if(endOfListReached && !refreshing) {
                                launchViewModel.loadLaunches(launchTypes[state])
                            }
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }

    // Launch details screen
    if (showDetailsView) {
        launchId?.let { id ->
            (if (launchTypes[state] == Launch.Type.UPCOMING) {
                upcomingLaunches
            } else {
                pastLaunches
            })?.find { it.id == id }?.let { launch ->
                LaunchDetailsScreen(
                    launch = launch,
                    showDetails = {
                        showDetailsView = it
                    }
                )
            }
        }
    }

    // WebView to sites for launch
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