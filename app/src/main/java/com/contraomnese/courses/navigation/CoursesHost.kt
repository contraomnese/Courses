package com.contraomnese.courses.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.contraomnese.courses.MainActivityEvent
import com.contraomnese.courses.core.ui.composition.LocalCoursesBackgrounds
import com.contraomnese.courses.core.ui.composition.LocalSnackbarHostState
import com.contraomnese.courses.core.ui.composition.coursesBackgrounds
import com.contraomnese.courses.features.auth.navigation.authentication
import com.contraomnese.courses.features.bottom_menu.navigation.bottomMenu
import com.contraomnese.courses.presentation.architecture.MviDestination
import com.contraomnese.courses.presentation.architecture.collectEvent
import com.contraomnese.courses.presentation.utils.handleError
import kotlinx.coroutines.flow.Flow

@Composable
internal fun CoursesHost(
    navController: NavHostController = rememberNavController(),
    eventFlow: Flow<MainActivityEvent>,
    startDestination: MviDestination,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    eventFlow.collectEvent { event ->
        when (event) {
            is MainActivityEvent.HandleError -> snackbarHostState.showSnackbar(
                message = event.cause.handleError(context),
                duration = SnackbarDuration.Short
            )
        }
    }

    CompositionLocalProvider(
        LocalSnackbarHostState provides snackbarHostState,
        LocalCoursesBackgrounds provides coursesBackgrounds
    ) {
        Box(
            modifier = Modifier
                .semantics {
                    testTagsAsResourceId = true
                }
                .fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = startDestination,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = 350,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(
                            durationMillis = 350,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                popEnterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(
                            durationMillis = 350,
                            easing = FastOutSlowInEasing
                        )
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(
                            durationMillis = 350,
                            easing = FastOutSlowInEasing
                        )
                    )
                }
            ) {
                authentication(externalNavigator = navController.authNavigator())
                bottomMenu(externalNavigator = navController.bottomMenuNavigator())
//                home(navigateToHome = { })
            }
        }
    }
}


