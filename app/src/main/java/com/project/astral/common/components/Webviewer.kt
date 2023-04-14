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
fun WebViewer(url: String, showViewer: (Boolean) -> Unit) {
    val state = rememberWebViewState(url)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    state.pageTitle?.let { pageTitle ->
                        Text(
                            text = pageTitle,
                            fontSize = 15.sp
                        )
                    }
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
                state = state,
                onCreated = { it.settings.javaScriptEnabled = true },
                onDispose = {
                    showViewer(false)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}