package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.contraomnese.courses.core.design.icon.CoursesIcons
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.padding16

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit = {},
    onSearchFocusChanged: (Boolean) -> Unit = {},
    isError: Boolean = false,
    enabled: Boolean = true,
    placeholder: String = "Search for a city",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
) {

    DefaultTextField(
        modifier = modifier,
        value = searchQuery,
        onValueChanged = onSearchQueryChanged,
        onTextFieldFocusChanged = onSearchFocusChanged,
        isError = isError,
        enabled = enabled,
        placeholder = placeholder,
        leadingIcon = leadingIcon ?: {
            Icon(
                imageVector = CoursesIcons.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface
            )
        },
        trailingIcon = trailingIcon,
        colors = searchFieldColors()
    )
}

@Composable
private fun searchFieldColors() = TextFieldDefaults.colors(
    focusedContainerColor = MaterialTheme.colorScheme.surface.copy(0.8f),
    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
    focusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.8f),
    unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
    focusedTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.8f),
    unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,
    errorLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.8f),
    errorTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(0.8f),
)

@Preview
@Composable
private fun SearchTextFieldPreview() {
    CoursesTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchTextField(
                searchQuery = "London",
                modifier = Modifier.padding(padding16)
            )
        }
    }
}