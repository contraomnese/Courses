package com.contraomnese.courses.core.design.theme

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Dark,
    secondary = Green,
    onSecondary = White,
    primaryContainer = DarkGray,
    onPrimaryContainer = White,
    secondaryContainer = Green,
    onSecondaryContainer = White,
    background = Dark,
    onBackground = White,
    surface = DarkGray,
    onSurface = White,
    surfaceVariant = LightGray,
    onSurfaceVariant = White,
    error = Color.Red,
    onError = Dark,
    outline = Stroke
)

@Composable
fun CoursesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && supportsDynamicTheming() -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = coursesTypography,
        content = content
    )
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S