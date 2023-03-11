package com.project.astral.features.news

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.project.astral.core.components.Text
import com.project.astral.data.models.Article
import kotlin.text.Typography.middleDot

@Composable
fun NewsFeedItem(
    article: Article,
//    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .width(400.dp)
            .wrapContentSize(),
//            .padding(12.dp)
//            .clip(
//                CutCornerShape(
//                    topEnd = 6.dp, bottomStart = 18.dp,
//                    topStart = 6.dp, bottomEnd = 6.dp
//                ),
//            )
//            .clickable(
//                interactionSource = MutableInteractionSource(),
//                indication = rememberRipple(bounded = true),
//                onClick = {
//                    onItemClicked(rocket.id.toInt())
//                }
//            ),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(Color.Transparent, MaterialTheme.colorScheme.inversePrimary)
            )
        ),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription= article.title,
                Modifier
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Text(
                    text = article.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${article.source} $middleDot ${article.getPublishedDuration()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,

                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = article.summary,
                    fontSize = 17.sp,
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}