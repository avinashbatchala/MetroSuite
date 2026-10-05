package com.metro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalMetroAccentColor = compositionLocalOf { MetroColors.Blue }
val LocalMetroDarkTheme = compositionLocalOf { true }
val LocalMetroBackground = compositionLocalOf { MetroColors.BackgroundBlack }
val LocalMetroForeground = compositionLocalOf { MetroColors.TextWhite }
val LocalMetroSurface = compositionLocalOf { MetroColors.SurfaceDark }
val LocalMetroSubtleText = compositionLocalOf { MetroColors.TextDim }

/**
 * Shared Windows 10 Mobile theme. Drives `MaterialTheme` with a flat Metro colour
 * scheme, the Segoe-like typography and fully square shapes, and exposes Metro
 * composition locals for convenience.
 */
@Composable
fun MetroTheme(
    accentColor: Color = MetroColors.Blue,
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val background = if (darkTheme) MetroColors.BackgroundBlack else MetroColors.BackgroundWhite
    val container = if (darkTheme) MetroColors.SurfaceDark else MetroColors.SurfaceLight
    val onBackground = if (darkTheme) MetroColors.TextWhite else MetroColors.TextBlack
    val subtle = if (darkTheme) MetroColors.TextDim else MetroColors.TextSubtle

    val tileHigh = if (darkTheme) Color(0xFF262626) else Color(0xFFE8E8E8)
    val tileHighest = if (darkTheme) Color(0xFF2E2E2E) else Color(0xFFDEDEDE)

    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = accentColor,
            onPrimary = Color.White,
            primaryContainer = accentColor,
            onPrimaryContainer = Color.White,
            inversePrimary = accentColor,
            secondary = accentColor,
            onSecondary = Color.White,
            background = background,
            onBackground = onBackground,
            surface = container,
            onSurface = onBackground,
            surfaceVariant = tileHigh,
            onSurfaceVariant = subtle,
            surfaceContainer = container,
            surfaceContainerHigh = tileHigh,
            surfaceContainerHighest = tileHighest,
            outline = subtle,
            outlineVariant = MetroColors.DividerDark
        )
    } else {
        lightColorScheme(
            primary = accentColor,
            onPrimary = Color.White,
            primaryContainer = accentColor,
            onPrimaryContainer = Color.White,
            inversePrimary = accentColor,
            secondary = accentColor,
            onSecondary = Color.White,
            background = background,
            onBackground = onBackground,
            surface = container,
            onSurface = onBackground,
            surfaceVariant = tileHigh,
            onSurfaceVariant = subtle,
            surfaceContainer = container,
            surfaceContainerHigh = tileHigh,
            surfaceContainerHighest = tileHighest,
            outline = subtle,
            outlineVariant = MetroColors.DividerLight
        )
    }

    CompositionLocalProvider(
        LocalMetroAccentColor provides accentColor,
        LocalMetroDarkTheme provides darkTheme,
        LocalMetroBackground provides background,
        LocalMetroForeground provides onBackground,
        LocalMetroSurface provides container,
        LocalMetroSubtleText provides subtle
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MetroMaterialTypography,
            shapes = MetroShapes,
            content = content
        )
    }
}
