package com.contraomnese.courses.core.ui.composition

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.contraomnese.courses.ui.R

val LocalSnackbarHostState = compositionLocalOf<SnackbarHostState> {
    error("No SnackbarHostState provided")
}

val LocalCoursesBackgrounds = staticCompositionLocalOf<Map<Long, Int>> {
    error("Weather backgrounds are not provided")
}

val coursesBackgrounds =
    mapOf(
        100L to R.drawable.courses100,
        101L to R.drawable.courses101,
        102L to R.drawable.courses102,
        103L to R.drawable.courses103,
        104L to R.drawable.courses104,
    )