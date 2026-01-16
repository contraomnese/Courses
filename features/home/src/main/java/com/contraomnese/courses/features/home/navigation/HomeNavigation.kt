package com.contraomnese.courses.features.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.core.navigation.TopLevelDestination
import com.contraomnese.courses.features.home.di.homeModule
import com.contraomnese.courses.features.home.presentation.HomeRoute
import com.contraomnese.courses.features.home.presentation.HomeViewModel
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object HomeDestination: MviDestination

data class HomeTopLevelDestination(
    override val iconId: Int = com.contraomnese.courses.core.design.R.drawable.home,
    override val titleId: Int = com.contraomnese.courses.navigation.R.string.home,
    override val route: HomeDestination = HomeDestination,
    override val destinationRoute: String = HomeDestination::class.java.name
) : TopLevelDestination

fun NavController.navigateToHome(navOptions: NavOptions? = null) =
    navigate(HomeDestination, navOptions)

interface HomeNavigator {
    fun onNavigateToUp()
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.home(
    externalNavigator: HomeNavigator
) {
    composable<HomeDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(homeModule) }

        val viewModel: HomeViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        HomeRoute(
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
        )
    }

}