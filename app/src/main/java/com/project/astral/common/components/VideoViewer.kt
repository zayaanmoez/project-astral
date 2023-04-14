package com.project.astral.common.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoViewer(url: String, showViewer: (Boolean) -> Unit) {
    val videoId = url.split("/").last().replace("watch?v=", "")
    val webViewState = rememberWebViewState(url = "https://www.youtube.com/embed/${videoId}")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = url,
                        fontSize = 15.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { showViewer(false) }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            WebView(
                state = webViewState,
                onCreated = {
                    it.settings.javaScriptEnabled = true
                    it.settings.allowFileAccess = true
                },
                onDispose = {
                    showViewer(false)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}