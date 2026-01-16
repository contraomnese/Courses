package com.contraomnese.courses.features.auth.navigation


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.navigation
import com.contraomnese.courses.features.auth.login.navigation.LoginDestination
import com.contraomnese.courses.features.auth.login.navigation.login
import com.contraomnese.courses.features.auth.register.navigation.register
import com.contraomnese.courses.presentation.architecture.MviDestination
import kotlinx.serialization.Serializable

@Serializable
object AuthGraph : MviDestination

fun NavController.navigateToAuth(navOptions: NavOptions? = null) =
    navigate(AuthGraph, navOptions)

interface AuthNavigator {
    fun onNavigateToLogin()
    fun onNavigateToRegister()
    fun onNavigateToHome()
}

fun NavGraphBuilder.authentication(
    externalNavigator: AuthNavigator,
    onNavigateToHome: () -> Unit
) {
    navigation<AuthGraph>(startDestination = LoginDestination) {
        login(
            onNavigateToRegister = externalNavigator::onNavigateToRegister,
            onNavigateToHome = onNavigateToHome
        )
        register(
            onNavigateToLogin = externalNavigator::onNavigateToLogin,
            onNavigateToHome = onNavigateToHome
        )
    }

}