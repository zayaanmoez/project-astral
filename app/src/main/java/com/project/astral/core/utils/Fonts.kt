package com.project.astral.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.project.astral.R

@Composable
fun astralFont() = FontFamily(Font(R.font.header, weight = FontWeight.Bold))

@Composable
fun regularFont() = FontFamily(Font(R.font.medium, weight = FontWeight.Medium))
