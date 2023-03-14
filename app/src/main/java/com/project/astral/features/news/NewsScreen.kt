package com.project.astral.features.news

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.astral.core.components.Text
import com.project.astral.core.components.WebViewer
import com.project.astral.core.utils.astralFont
import com.project.astral.viewmodels.NewsViewModel

@Composable
fun NewsScreen(newsViewModel: NewsViewModel = hiltViewModel()) {
    val newsFeed by newsViewModel.articleList.observeAsState()
    var showWebView by rememberSaveable {
        mutableStateOf(false)
    }
    var webViewUrl by rememberSaveable {
        mutableStateOf("www.google.com")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { newsViewModel.loadArticles() },
                    ) {
                        Text(
                            text = "Load",
                            fontSize = 15.sp,
                            color = Color.DarkGray)
                    }
                }
            }
        }
    }

    if (showWebView) {
        WebViewer(url = webViewUrl, showViewer = {
            showWebView = it
        })
    }
}