package com.project.astral.features

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.astral.core.components.Text
import com.project.astral.core.utils.astralFont
import com.project.astral.data.models.Article
import com.project.astral.viewmodels.NewsViewModel

@Composable
fun NewsScreen() {
    val newsViewModel = hiltViewModel<NewsViewModel>()
    val newsFeed by newsViewModel.articleList.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(4.dp)
        ) {
            item {
                Text(
                    text = "News Feed",
                    font = astralFont(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            newsFeed?.let { it ->
                items(it) { article ->
                    Text(
                        text = article.title
                    )
                }
            }
        }
    }
}