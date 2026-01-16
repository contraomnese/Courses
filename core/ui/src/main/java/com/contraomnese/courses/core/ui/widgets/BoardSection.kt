package com.contraomnese.courses.core.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.padding20
import com.contraomnese.courses.core.design.theme.padding24
import com.contraomnese.courses.ui.R as UI

@Composable
fun BoardSection(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {

    Box(
        modifier = modifier
            .wrapContentWidth()
            .clip(CircleShape)
            .background(
                containerColor
            )
    ) {
        Text(
            modifier = Modifier.padding(horizontal = padding24, vertical = padding20),
            text = stringResource(text),
            style = MaterialTheme.typography.labelLarge.copy(
                color = contentColor
            )
        )
    }
}

@Preview
@Composable
fun BoardSectionPreview() {
    CoursesTheme {
        MenuSection(
            modifier = Modifier
                .height(33.dp),
            text = UI.string.logout_title
        )
    }
}