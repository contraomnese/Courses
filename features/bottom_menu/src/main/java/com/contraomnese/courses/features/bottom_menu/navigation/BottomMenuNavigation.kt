package com.contraomnese.courses.features.bottom_menu.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.contraomnese.courses.core.navigation.navigateSingleTopTo
import com.contraomnese.courses.features.bottom_menu.di.bottomMenuModule
import com.contraomnese.courses.features.bottom_menu.presentation.BottomMenuRoute
import com.contraomnese.courses.features.bottom_menu.presentation.BottomMenuViewModel
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object BottomMenuGraph: MviDestination

@Serializable
object BottomMenuDestination

interface BottomMenuNavigator {
    fun onLogOut()
    fun onNavigateUp()
}

fun NavGraphBuilder.bottomMenu(
    externalNavigator: BottomMenuNavigator,
    onLogOut: () -> Unit
) {
    navigation<BottomMenuGraph>(startDestination = BottomMenuDestination) {
        bottomMenuInner(
            externalNavigator = externalNavigator,
            onLogOut = onLogOut
        )
    }
}

@OptIn(KoinExperimentalAPI::class)
private fun NavGraphBuilder.bottomMenuInner(
    externalNavigator: BottomMenuNavigator,
    onLogOut: () -> Unit
) {

    composable<BottomMenuDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(bottomMenuModule) }

        val viewModel: BottomMenuViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        BottomMenuRoute(
            externalNavigator = externalNavigator,
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
            onLogOut = onLogOut
        )

    }
}

fun NavHostController.navigateToBottomMenu() {
    popBackStack()
    navigateSingleTopTo(BottomMenuGraph)
}