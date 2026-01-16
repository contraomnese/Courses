package com.contraomnese.courses.features.auth.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.features.auth.login.di.loginModule
import com.contraomnese.courses.features.auth.login.presentation.LoginRoute
import com.contraomnese.courses.features.auth.login.presentation.LoginViewModel
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object LoginDestination: MviDestination

fun NavController.navigateToLogin(navOptions: NavOptions? = null) =
    navigate(LoginDestination, navOptions)

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.login(
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    composable<LoginDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf(loginModule) }

        val viewModel: LoginViewModel = koinViewModel(viewModelStoreOwner = backStackEntry)

        LoginRoute(
            viewModel = viewModel,
            eventFlow = viewModel.eventFlow,
            pushAction = viewModel::push,
            onNavigateToRegister = onNavigateToRegister,
            onNavigateToHome = onNavigateToHome
        )
    }

}