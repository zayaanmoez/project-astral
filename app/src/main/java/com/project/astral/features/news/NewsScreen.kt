package com.project.astral.features.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.astral.common.components.Text
import com.project.astral.common.components.WebViewer
import com.project.astral.common.utils.astralFont
import com.project.astral.common.utils.isScrolledToTheEnd
import com.project.astral.viewmodels.NewsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NewsScreen(newsViewModel: NewsViewModel = hiltViewModel()) {
    val newsFeed by newsViewModel.articleList.observeAsState()
    val scrollState = rememberLazyListState()
    var showWebView by rememberSaveable { mutableStateOf(false) }
    var webViewUrl by rememberSaveable { mutableStateOf("") }
    val refreshScope = rememberCoroutineScope()
    var refreshing by rememberSaveable { mutableStateOf(false) }

    // Reload data on pull refresh
    val refreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = {
            refreshScope.launch {
                refreshing = true
                newsViewModel.reloadArticles()
                delay(1000)
                refreshing = false
            }
        }
    )

    // observer when reached end of list to load more articles
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToTheEnd()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .pullRefresh(refreshState)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = scrollState
        ) {
            if (!refreshing) {
                item {
                    Text(
                        text = "News Feed",
                        font = astralFont(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                newsFeed?.let { it ->
                    items(it) { article ->
                        NewsFeedItem(article, showWebViewer = { url ->
                            showWebView = true
                            webViewUrl = url
                        })
                        Divider(modifier = Modifier.padding(horizontal = 20.dp))
                        Spacer(modifier = Modifier.height(30.dp))
                    }
                }
                if (newsFeed?.isNotEmpty() == true) {
                    item {
                        // Load more articles if end of list is reached
                        LaunchedEffect(endOfListReached) {
                            if(endOfListReached && !refreshing) {
                                newsViewModel.loadArticles()
                            }
                        }
                    }
                }
            }
        }

        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }

    // WebView to view articles
    if (showWebView) {
        WebViewer(url = webViewUrl, showViewer = {
            showWebView = it
        })
    }
}