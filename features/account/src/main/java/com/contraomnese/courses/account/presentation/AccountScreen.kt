package com.contraomnese.courses.account.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.contraomnese.courses.core.design.theme.CoursesTheme
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.widgets.LoadingIndicator
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import kotlinx.coroutines.flow.Flow

@Composable
internal fun AccountRoute(
    viewModel: AccountViewModel,
    eventFlow: Flow<AccountEvent>,
    pushAction: (AccountAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val snackBarHostState = LocalSnackbarHostState.current

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    eventFlow.collectEvent { event ->
        when (event) {
            is AccountEvent.HandleError -> snackBarHostState.showSnackbar(
                message = event.cause.handleError(context),
                duration = SnackbarDuration.Short
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading ->
                LoadingIndicator(
                    Modifier
                        .align(Alignment.Center)
                        .width(itemWidth64)
                )

            else -> {
                AccountScreen(
                    uiState = uiState,
                    pushAction = pushAction,
                )
            }
        }
    }
}

@Composable
private fun AccountScreen(
    uiState: AccountState,
    pushAction: (AccountAction) -> Unit,
) {

}

@Preview(locale = "ru")
@Composable
private fun AccountScreenPreview() {
    CoursesTheme {
        AccountScreen(
            uiState = AccountState.DEFAULT,
            pushAction = {},
        )
    }
}
