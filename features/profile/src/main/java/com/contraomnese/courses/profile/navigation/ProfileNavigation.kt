package com.contraomnese.courses.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.core.navigation.TopLevelDestination
import com.contraomnese.courses.presentation.architecture.MviDestination
import com.contraomnese.courses.profile.di.profileModule
import com.contraomnese.courses.profile.presentation.ProfileRoute
import com.contraomnese.courses.profile.presentation.ProfileViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object ProfileDestination: MviDestination

data class ProfileTopLevelDestination(
    override val iconId: Int = com.contraomnese.courses.core.design.R.drawable.profile,
    override val titleId: Int = com.contraomnese.courses.navigation.R.string.profile,
    override val route: ProfileDestination = ProfileDestination,
    override val destinationRoute: String = ProfileDestination::class.java.name
) : TopLevelDestination

fun NavController.navigateToProfile(navOptions: NavOptions? = null) =
    navigate(ProfileDestination, navOptions)

interface ProfileNavigator {
    fun onNavigateToUp()
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.profile(
    externalNavigator: ProfileNavigator,
    onLogOut: () -> Unit
) {
    composable<ProfileDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(profileModule) }

        val viewModel: ProfileViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        ProfileRoute(
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
            onLogOut = onLogOut
        )
    }
}