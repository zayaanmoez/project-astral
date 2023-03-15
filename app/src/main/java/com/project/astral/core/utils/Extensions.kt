package com.project.astral.core.utils

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToTheEnd() : Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem == null || (lastItem.index == layoutInfo.totalItemsCount - 1 && lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset)
}