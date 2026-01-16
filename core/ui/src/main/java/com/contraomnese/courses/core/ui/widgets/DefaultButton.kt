package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius24
import com.contraomnese.courses.core.design.theme.defaultButtonHeight
import com.contraomnese.courses.core.design.theme.padding12

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    title: String,
    enabled: Boolean = true,
    onClicked: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(defaultButtonHeight),
        onClick = onClicked,
        contentPadding = PaddingValues(vertical = padding12),
        colors = ButtonColors(
            contentColor = containerColor,
            containerColor = containerColor,
            disabledContentColor = containerColor.copy(alpha = 0.1f),
            disabledContainerColor = containerColor.copy(alpha = 0.1f)
        ),
        enabled = enabled,
        shape = RoundedCornerShape(cornerRadius24)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun DefaultButtonPreview() {
    CoursesTheme {
        DefaultButton(
            title = "Title",
            onClicked = { }
        )
    }
}