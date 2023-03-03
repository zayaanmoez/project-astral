package com.project.astral.core.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.project.astral.core.utils.astralFont

@Composable
fun Text(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 12.sp,
    font: FontFamily = astralFont(),
    fontWeight: FontWeight = FontWeight.Normal,
    color: Color = MaterialTheme.colorScheme.secondary,
    letterSpacing: TextUnit = 0.5.sp,
    textAlign: TextAlign = TextAlign.Start,
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = font,
        fontWeight = fontWeight,
        textAlign = textAlign,
        letterSpacing = letterSpacing,
        modifier = modifier
    )
}