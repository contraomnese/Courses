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

val boardCourses =
    listOf(
        R.string.android_development,
        R.string.cloud_computing,
        R.string.software_architecture,
        R.string.backend_development,
        R.string.machine_learning,
        R.string.ui_ux_design,
        R.string.devops,
        R.string.blockchain_development,
        R.string.frontend_development,
        R.string.cybersecurity,
        R.string.game_development,
        R.string.data_science,
        R.string.project_management,
        R.string.low_code,
        R.string.qa_automation,
        R.string.aws_fundamentals,
        R.string.it_business_analysis,
        R.string.mobile_security,
        R.string.fullstack_development,
        R.string.ethical_hacking,
        R.string.docker_kubernetes,
        R.string.ios_development,
        R.string.software_testing,
        R.string.data_analysis,
        R.string.product_management,
        R.string.big_data,
        R.string.web_development,
        R.string.artificial_intelligence,
        R.string.digital_marketing,
        R.string.project_management,
    )