package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.contraomnese.courses.core.design.icon.CoursesIcons
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.ui.utils.colorSelectorByFocus
import com.contraomnese.courses.ui.R

@Composable
fun PasswordFormTextField(
    value: String,
    label: String,
    onValueChanged: (String) -> Unit = {},
    hasError: Boolean = false,
    modifier: Modifier = Modifier,
) {
    var isVisible by remember {
        mutableStateOf(false)
    }

    var isFocused by remember {
        mutableStateOf(false)
    }

    val visibilityIcon by remember {
        derivedStateOf {
            if (isVisible) CoursesIcons.VisibilityOff
            else CoursesIcons.Visibility
        }
    }
    val visibilityTransformation by remember {
        derivedStateOf {
            if (isVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        }
    }

    TextFieldWidget(
        modifier = modifier,
        value = value,
        label = label,
        isError = hasError,
        onValueChanged = onValueChanged,
        onTextFieldFocusChanged = { isFocused = it },
        visualTransformation = visibilityTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon =
            {
                Icon(
                    modifier = Modifier.clickable { isVisible = !isVisible },
                    imageVector = visibilityIcon,
                    contentDescription = null,
                    tint = colorSelectorByFocus(isFocused, MaterialTheme.colorScheme.onSurface)
                )
            },
        placeholder = stringResource(R.string.password_placeholder)
    )
}


@Preview
@Composable
fun PreviewPasswordFormTextField() {
    CoursesTheme {
        PasswordFormTextField(
            modifier = Modifier.fillMaxWidth(),
            value = "",
            label = "Password",
            hasError = false,
            onValueChanged = {})
    }
}