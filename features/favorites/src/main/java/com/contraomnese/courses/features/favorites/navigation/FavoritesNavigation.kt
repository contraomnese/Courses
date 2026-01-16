package com.contraomnese.courses.features.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.core.navigation.TopLevelDestination
import com.contraomnese.courses.features.favorites.di.favoritesModule
import com.contraomnese.courses.features.favorites.presentation.FavoritesRoute
import com.contraomnese.courses.features.favorites.presentation.FavoritesViewModel
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object FavoritesDestination: MviDestination

data class FavoritesTopLevelDestination(
    override val iconId: Int = com.contraomnese.courses.core.design.R.drawable.favorite_outline,
    override val titleId: Int = com.contraomnese.courses.navigation.R.string.favorites,
    override val route: FavoritesDestination = FavoritesDestination,
    override val destinationRoute: String = FavoritesDestination::class.java.name
) : TopLevelDestination

fun NavController.navigateToFavorites(navOptions: NavOptions? = null) =
    navigate(FavoritesDestination, navOptions)

interface FavoritesNavigator {
    fun onNavigateToUp()
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.favorites(
    externalNavigator: FavoritesNavigator
) {
    composable<FavoritesDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(favoritesModule) }

        val viewModel: FavoritesViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        FavoritesRoute(
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
        )
    }

}