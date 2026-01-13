package com.contraomnese.courses.features.auth.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object RegisterDestination: MviDestination

fun NavController.navigateToRegister(navOptions: NavOptions? = null) =
    navigate(RegisterDestination, navOptions)

interface RegisterNavigator {
    fun onNavigateToRegister()
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.register(
    navigateToLogin: () -> Unit
) {
    composable<RegisterDestination> { backStackEntry ->

        rememberKoinModules(unloadOnForgotten = true) { listOf() }
    }

}