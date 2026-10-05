package com.metro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.metro.ui.R

/**
 * Windows 10 Mobile "Segoe-like" typography, approximated with Open Sans:
 * light display weights and compact labels create the Metro hierarchy.
 */
val MetroFontFamily = FontFamily(
    Font(R.font.open_sans, FontWeight.Light),
    Font(R.font.open_sans, FontWeight.Normal),
    Font(R.font.open_sans, FontWeight.Medium),
    Font(R.font.open_sans, FontWeight.SemiBold),
    Font(R.font.open_sans, FontWeight.Bold)
)

object MetroTypography {
    val startTitle = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 42.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.5).sp,
        color = MetroColors.TextWhite
    )

    val pageHeader = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 38.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.4).sp,
        color = MetroColors.TextWhite
    )

    val tileLabel = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.5.sp,
        lineHeight = 15.sp,
        letterSpacing = (-0.1).sp,
        color = MetroColors.TextWhite,
        shadow = Shadow(color = Color(0xDD000000), offset = Offset(1f, 1f), blurRadius = 3f)
    )

    val tileLabelLarge = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.5.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.1).sp,
        color = MetroColors.TextWhite,
        shadow = Shadow(color = Color(0xDD000000), offset = Offset(1f, 1f), blurRadius = 3f)
    )

    val appListGroupHeader = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 32.sp,
        lineHeight = 36.sp,
        color = MetroColors.TextWhite
    )

    val appListItem = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 17.5.sp,
        lineHeight = 22.sp,
        color = MetroColors.TextWhite
    )

    val searchHint = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = MetroColors.TextDim
    )

    val searchInput = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = MetroColors.TextWhite
    )

    val buttonLabel = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        letterSpacing = 0.5.sp,
        color = MetroColors.TextWhite
    )

    val settingsTitle = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 42.sp,
        lineHeight = 48.sp,
        letterSpacing = (-0.5).sp
    )

    val settingsSection = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.5.sp
    )

    val settingsLabel = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp
    )

    val settingsSubtext = TextStyle(
        fontFamily = MetroFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp
    )
}

/** Material3 [Typography] wired to the Metro font family for `MaterialTheme`. */
val MetroMaterialTypography: Typography = Typography().run {
    copy(
        displayLarge = displayLarge.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 57.sp, lineHeight = 62.sp),
        displayMedium = displayMedium.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 45.sp, lineHeight = 50.sp),
        displaySmall = displaySmall.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 36.sp, lineHeight = 42.sp),
        headlineLarge = headlineLarge.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 32.sp, lineHeight = 38.sp),
        headlineMedium = headlineMedium.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 28.sp, lineHeight = 34.sp),
        headlineSmall = headlineSmall.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 24.sp, lineHeight = 30.sp),
        titleLarge = titleLarge.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Light, fontSize = 22.sp, lineHeight = 28.sp),
        titleMedium = titleMedium.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 22.sp),
        titleSmall = titleSmall.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
        bodyLarge = bodyLarge.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 22.sp),
        bodyMedium = bodyMedium.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
        bodySmall = bodySmall.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),
        labelLarge = labelLarge.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
        labelMedium = labelMedium.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),
        labelSmall = labelSmall.copy(fontFamily = MetroFontFamily, fontWeight = FontWeight.Normal, fontSize = 11.sp, lineHeight = 16.sp)
    )
}
