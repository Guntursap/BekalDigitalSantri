package com.guntur.santripunya.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val lightScheme = lightColorScheme(
    primary = primaryLight,
    secondary = secondaryLight,
    tertiary = tertiaryLight,
    surface = surfaceVariantLight,
    background = surfaceLight,
    surfaceVariant = surfaceVariantLight
)

@Composable
fun SantriPunyaTheme(
    // Dynamic color is available on Android 12+
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colorScheme = lightScheme,
        typography = AppTypography,
        content = content
    )
}

