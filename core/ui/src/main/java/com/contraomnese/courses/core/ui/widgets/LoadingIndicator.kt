package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.design.theme.thickness2
import com.contraomnese.courses.core.ui.DevicePreviews

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    thickness: Dp = thickness2,
) {
    CircularProgressIndicator(
        modifier = modifier.size(itemWidth64),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        trackColor = Color.Transparent,
        strokeWidth = thickness,
        strokeCap = StrokeCap.Square
    )
}

@DevicePreviews
@Composable
fun LoadingIndicatorPreview(modifier: Modifier = Modifier) {
    CoursesTheme {
        Box(Modifier.fillMaxSize()) {
            LoadingIndicator(
                Modifier.align(Alignment.Center)
            )
        }
    }
}