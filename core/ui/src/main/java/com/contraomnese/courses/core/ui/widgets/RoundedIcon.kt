package com.contraomnese.courses.core.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.contraomnese.courses.core.design.R
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius32
import com.contraomnese.courses.core.design.theme.itemHeight40
import com.contraomnese.courses.core.design.theme.itemWidth48
import com.contraomnese.courses.core.design.theme.padding12
import com.contraomnese.courses.core.ui.DevicePreviews

@Composable
fun RoundedIcon(
    modifier: Modifier = Modifier,
    radius: Dp = cornerRadius32,
    description: String,
    @DrawableRes icon: Int,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding12)
            .height(itemHeight40)
            .clip(RoundedCornerShape(radius))
            .clickable(
                onClick = onClick,
                enabled = enabled
            )
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = description,
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

@DevicePreviews
@Composable
private fun RoundedIconPreview() {
    CoursesTheme {
        RoundedIcon(
            modifier = Modifier.background(Color(0xFF2683ED)),
            onClick = {},
            description = "vocibus",
            icon = R.drawable.vk,
            enabled = true
        )
    }
}