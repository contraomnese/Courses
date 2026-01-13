package com.contraomnese.courses.features.profile.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.contraomnese.courses.core.design.icon.VibeShotIcons
import com.contraomnese.courses.core.navigation.TopLevelDestination
import com.contraomnese.courses.features.profile.di.profileModule
import com.contraomnese.courses.features.profile.presentation.ProfileRoute
import com.contraomnese.courses.ui.R
import kotlinx.serialization.Serializable
import org.koin.compose.module.rememberKoinModules
import org.koin.core.annotation.KoinExperimentalAPI

@Serializable
object ProfileDestination

data class ProfileTopLevelDestination(
    override val icon: ImageVector = VibeShotIcons.Profile,
    override val titleId: Int = R.string.profile,
    override val route: Any = ProfileDestination,
    override val destinationRoute: String = ProfileDestination::class.java.name

): TopLevelDestination

fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(ProfileDestination, navOptions)
}

@OptIn(KoinExperimentalAPI::class)
fun NavGraphBuilder.profile(onLogOutClicked: () -> Unit) {

    composable<ProfileDestination> {
        rememberKoinModules(unloadOnForgotten = true) { listOf(profileModule) }

        ProfileRoute(onNavigateToAuth = onLogOutClicked)
    }
}