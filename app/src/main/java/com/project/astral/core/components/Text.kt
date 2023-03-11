package com.project.astral.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.project.astral.core.utils.regularFont

@Composable
fun Text(
    text: String,
    font: FontFamily = regularFont(),
    fontSize: TextUnit = 12.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.secondary,
    lineHeight: TextUnit = 10.sp,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontFamily = font,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        lineHeight = fontSize,
        textAlign = textAlign,
        modifier = modifier
    )
}