package com.contraomnese.courses.core.ui.widgets

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.contraomnese.courses.core.design.icon.CoursesIcons
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius30
import com.contraomnese.courses.core.design.theme.itemHeight40
import com.contraomnese.courses.core.design.theme.padding12
import com.contraomnese.courses.core.design.theme.padding20
import com.contraomnese.courses.core.design.theme.space8
import com.contraomnese.courses.core.ui.utils.colorSelectorByFocus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWidget(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    onTextFieldFocusChanged: (Boolean) -> Unit = {},
    isError: Boolean = false,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Go),
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {

    var textFieldValueState by remember {
        mutableStateOf(TextFieldValue(value))
    }

    val focusRequester = remember { FocusRequester() }
    val currentKeyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val containerColor: Color = MaterialTheme.colorScheme.surfaceVariant
    val innerContainerColor: Color = MaterialTheme.colorScheme.onSurfaceVariant
    val outlineColor: Color = MaterialTheme.colorScheme.primary

    LaunchedEffect(isFocused) {
        onTextFieldFocusChanged(isFocused)
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space8)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = outlineColor,
            textAlign = TextAlign.Start
        )
        BasicTextField(
            value = textFieldValueState,
            onValueChange = { newValue ->
                textFieldValueState = newValue
                onValueChanged(textFieldValueState.text)
            },
            modifier = Modifier
                .height(itemHeight40)
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            singleLine = singleLine,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = colorSelectorByFocus(isFocused, innerContainerColor)
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onGo = {
                    focusManager.clearFocus(force = true)
                    currentKeyboardController?.hide()
                }
            ),
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            cursorBrush = SolidColor(outlineColor),
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = textFieldValueState.text,
                    innerTextField = innerTextField,
                    enabled = enabled,
                    isError = isError,
                    singleLine = singleLine,
                    leadingIcon = leadingIcon,
                    trailingIcon = trailingIcon ?: {
                        if (textFieldValueState.text.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    textFieldValueState = TextFieldValue("")
                                    onValueChanged("")
                                },
                            ) {
                                Icon(
                                    imageVector = CoursesIcons.Close,
                                    contentDescription = null,
                                    tint = colorSelectorByFocus(isFocused, innerContainerColor)
                                )
                            }
                        }
                    },
                    placeholder = {
                        Text(
                            text = placeholder,
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
                            textAlign = TextAlign.Start
                        )
                    },
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    contentPadding = PaddingValues(horizontal = padding20, vertical = padding12),
                    container = {
                        TextFieldDefaults.Container(
                            enabled = enabled,
                            isError = isError,
                            interactionSource = interactionSource,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = colorSelectorByFocus(isFocused, containerColor),
                                unfocusedContainerColor = colorSelectorByFocus(isFocused, containerColor),
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                errorContainerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f),
                                errorBorderColor = Color.Transparent
                            ),
                            shape = RoundedCornerShape(cornerRadius30),
                        )
                    }
                )
            }
        )
    }
}

@Preview
@Composable
private fun TextFieldPlaceholderPreview() {
    CoursesTheme  {
        TextFieldWidget(
            label = "Title",
            value = "",
            onValueChanged = {},
            onTextFieldFocusChanged = {},
            isError = false,
            singleLine = true,
            placeholder = "placeholder",
        )
    }
}

@Preview
@Composable
private fun TextFieldValuePreview() {
    CoursesTheme  {
        TextFieldWidget(
            label = "Title",
            value = "value",
            onValueChanged = {},
            onTextFieldFocusChanged = {},
            isError = false,
            singleLine = true,
            placeholder = "placeholder",
        )
    }
}

@Preview
@Composable
private fun TextFieldIsErrorPreview() {
    CoursesTheme {
        TextFieldWidget(
            label = "Title",
            value = "value",
            onValueChanged = {},
            onTextFieldFocusChanged = {},
            isError = true,
            singleLine = true,
            placeholder = "placeholder",
        )
    }
}