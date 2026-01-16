package com.contraomnese.courses.features.auth.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.features.auth.register.di.registerModule
import com.contraomnese.courses.features.auth.register.presentation.RegisterRoute
import com.contraomnese.courses.features.auth.register.presentation.RegisterViewModel
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object RegisterDestination: MviDestination

fun NavController.navigateToRegister(navOptions: NavOptions? = null) =
    navigate(RegisterDestination, navOptions)

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.register(
    onNavigateToLogin: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<RegisterDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(registerModule) }

        val viewModel: RegisterViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        RegisterRoute(
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
            onNavigateToLogin = onNavigateToLogin,
            onNavigateToHome = onNavigateToHome
        )

    }

}