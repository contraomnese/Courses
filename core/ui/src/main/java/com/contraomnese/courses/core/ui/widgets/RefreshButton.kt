package com.contraomnese.courses.core.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import com.contraomnese.courses.core.design.icon.CoursesIcons
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.ui.DevicePreviews
import com.contraomnese.courses.ui.R

@Composable
fun RefreshButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    @StringRes description: Int,
    enabled: Boolean = true
) {

    Image(
        modifier = modifier
            .clip(CircleShape)
            .clickable(
                onClick = onClick,
                enabled = enabled
            ),
        imageVector = CoursesIcons.Refresh,
        contentDescription = stringResource(description),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
    )
}

@DevicePreviews
@Composable
private fun RefreshButtonPreview() {
    CoursesTheme {
        RefreshButton(
            onClick = {}, description = R.string.app_name
        )
    }
}