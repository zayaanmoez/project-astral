package com.project.astral.features.launch.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.astral.core.components.Text
import com.project.astral.core.utils.astralFont
import com.project.astral.data.models.Article
import com.project.astral.features.news.NewsFeedItem

@Composable
fun RelatedNewsCard(
    articles: List<Article>,
    showWebViewer: (String) -> Unit)
{
    Box(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Related News",
                font = astralFont(),
                fontSize = 30.sp,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 30.dp)
                    .padding(top = 5.dp, bottom = 10.dp)
            )
            Spacer(modifier = Modifier.height(30.dp))
            articles.forEach { article ->
                NewsFeedItem(article, showWebViewer = showWebViewer)
                Divider(modifier = Modifier.padding(horizontal = 20.dp))
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}