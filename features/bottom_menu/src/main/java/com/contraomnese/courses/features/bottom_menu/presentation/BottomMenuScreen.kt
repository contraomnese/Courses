package com.contraomnese.courses.features.bottom_menu.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.contraomnese.courses.account.navigation.account
import com.contraomnese.courses.core.design.theme.itemWidth64
import com.contraomnese.courses.core.navigation.navigateSingleTopTo
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.widgets.CoursesSnackBarHost
import com.contraomnese.courses.core.ui.widgets.LoadingIndicator
import com.contraomnese.courses.core.ui.widgets.NavBar
import com.contraomnese.courses.features.bottom_menu.navigation.BottomMenuNavigator
import com.contraomnese.courses.features.home.navigation.HomeDestination
import com.contraomnese.courses.features.home.navigation.home
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import kotlinx.coroutines.flow.Flow

@Composable
internal fun BottomMenuRoute(
    modifier: Modifier = Modifier,
    viewModel: BottomMenuViewModel,
    eventFlow: Flow<BottomMenuEvent>,
    pushAction: (BottomMenuAction) -> Unit,
    externalNavigator: BottomMenuNavigator,
) {

    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val context = LocalContext.current
    val snackBarHostState = LocalSnackbarHostState.current

    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()

    eventFlow.collectEvent { event ->
        when (event) {
            is BottomMenuEvent.HandleError -> snackBarHostState.showSnackbar(
                message = event.cause.handleError(context),
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavBar(
                currentDestination = currentRoute,
                destinations = uiState.topLevelDestinations,
                onNavigateToTopLevel = { route ->
                    navController.navigateSingleTopTo(route)
                },
            )
        },
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
                            .fillMaxSize()
                            .align(Alignment.Center)
                            .width(itemWidth64)
                    )

                else -> {
                    NavHost(
                        navController = navController,
                        startDestination = HomeDestination
                    ) {
                        home {  }
                        account {  }
                    }
                }
            }
        }
    }
}
