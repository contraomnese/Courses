package com.contraomnese.courses.features.auth.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.cornerRadius32
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.design.theme.padding100
import com.contraomnese.courses.core.design.theme.padding16
import com.contraomnese.courses.core.design.theme.padding24
import com.contraomnese.courses.core.design.theme.padding28
import com.contraomnese.courses.core.design.theme.padding32
import com.contraomnese.courses.core.design.theme.paddingZero
import com.contraomnese.courses.core.design.theme.space16
import com.contraomnese.courses.core.design.theme.thickness1
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.widgets.CoursesSnackBarHost
import com.contraomnese.courses.core.ui.widgets.DefaultButton
import com.contraomnese.courses.core.ui.widgets.LoadingIndicator
import com.contraomnese.courses.core.ui.widgets.PasswordFormTextField
import com.contraomnese.courses.core.ui.widgets.RoundedIcon
import com.contraomnese.courses.core.ui.widgets.TextFieldWidget
import com.contraomnese.courses.features.auth.register.navigation.RegisterDestination
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import com.contraomnese.courses.ui.R
import kotlinx.coroutines.flow.Flow

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel,
    eventFlow: Flow<LoginEvent>,
    pushAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val snackBarHostState = LocalSnackbarHostState.current

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    eventFlow.collectEvent { event ->
        when (event) {
            is LoginEvent.HandleError -> snackBarHostState.showSnackbar(
                message = event.cause.handleError(context),
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { CoursesSnackBarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets.statusBars,
        containerColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading ->
                    LoadingIndicator(
                        Modifier
                            .width(itemWidth64)
                            .align(Alignment.Center)
                    )

                else -> {
                    LoginScreen(
                        uiState = uiState,
                        pushAction = pushAction,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
internal fun LoginScreen(
    uiState: LoginState,
    pushAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Text(
            modifier = Modifier
                .padding(top = padding100, start = padding16, end = padding16)
                .align(Alignment.Start),
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Column(
            modifier = modifier
                .padding(top = padding28, start = padding16, end = padding16),
            verticalArrangement = Arrangement.spacedBy(space16),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWidget(
                label = stringResource(R.string.email_title),
                value = uiState.login.value,
                onValueChanged = {
                    pushAction(LoginAction.LoginChange(it))
                },
                isError = !uiState.login.isValidEmail(),
                placeholder = "mail@mail.mail",
            )
            PasswordFormTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password.value,
                label = stringResource(R.string.password_title),
                onValueChanged = {
                    pushAction(LoginAction.PasswordChange(it))
                },
            )
            DefaultButton(
                modifier = Modifier.padding(top = padding24),
                title = stringResource(R.string.login_title),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                enabled = uiState.isValid(),
                onClicked = {
                    keyboardController?.hide()
                }
            )
        }

        NotAccount(modifier = Modifier
            .padding(top = padding16)
            .align(Alignment.CenterHorizontally))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding32, start = padding16, end = padding16),
            thickness = thickness1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = padding32),
            horizontalArrangement = Arrangement.spacedBy(space16)
        ) {
            RoundedIcon(
                modifier = Modifier
                    .background(Color(0xFF2683ED))
                    .weight(1f),
                radius = cornerRadius32,
                icon = com.contraomnese.courses.core.design.R.drawable.vk,
                description = "go_to_vk"
            ) {
                uriHandler.openUri("https://vk.com/")
            }
            RoundedIcon(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color(0xFFF98509),
                                Color(0xFFF95D00),
                            )
                        )
                    )
                    .weight(1f),
                radius = cornerRadius32,
                icon = com.contraomnese.courses.core.design.R.drawable.ok,
                description = "go_to_ok"
            ) {
                uriHandler.openUri("https://ok.ru/")
            }
        }
    }
}

@Composable
private fun NotAccount(
    modifier: Modifier = Modifier,
    description: String = stringResource(R.string.not_account),
    linkDescription: String = stringResource(R.string.register_title)
) {
    val textStyle = MaterialTheme.typography.labelSmall.toSpanStyle().copy(
        color = MaterialTheme.colorScheme.onSurface,
    )

    val linkStyle = MaterialTheme.typography.labelSmall.toSpanStyle().copy(
        color = MaterialTheme.colorScheme.secondary
    )

    val notAccount = remember {
        buildAnnotatedString {

            withStyle(textStyle) {
                append(description)
            }
            append(" ")
            withLink(
                link = LinkAnnotation.Url(
                    url = RegisterDestination::class.qualifiedName!!,
                    styles = TextLinkStyles(style = linkStyle),
                    linkInteractionListener = {

                    }
                )
            ) {
                append(linkDescription)
            }
        }
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = notAccount,
            style = MaterialTheme.typography.labelSmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        TextButton(
            onClick = { },
            contentPadding = PaddingValues(paddingZero),
        ) {
            Text(
                text = stringResource(R.string.forget_password),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.secondary
                )
            )
        }
    }
}

@Preview(locale = "ru")
@Composable
private fun LoginScreenPreview() {
    CoursesTheme {
        LoginScreen(
            uiState = LoginState(),
            pushAction = {},
        )
    }
}
