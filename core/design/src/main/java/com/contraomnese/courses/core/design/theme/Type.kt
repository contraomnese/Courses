package com.contraomnese.courses.core.design.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.contraomnese.courses.core.design.R


private val robotoFamily = FontFamily(
    Font(resId = R.font.roboto_regular, weight = FontWeight.W400),
    Font(resId = R.font.roboto_medium, weight = FontWeight.W500),
    Font(resId = R.font.roboto_semibold, weight = FontWeight.W600),
    Font(resId = R.font.roboto_bold, weight = FontWeight.W700)
)

val coursesTypography = Typography(
    headlineMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W400,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W400,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.009375.em
    ),
    bodyMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.033.em
    ),
    bodySmall = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        lineHeight = 15.sp,
        letterSpacing = 0.033.em
    ),
    labelLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    displayMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.033.em
    )
)