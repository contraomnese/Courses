package com.contraomnese.courses.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.collections.immutable.ImmutableList

data class TopDestinationsCollection(val items: ImmutableList<TopLevelDestination>)

interface TopLevelDestination {
    @get:DrawableRes
    val iconId: Int
    @get:StringRes
    val titleId: Int
    val route: Any
    val destinationRoute: String
}

fun NavHostController.navigateSingleTopTo(route: Any) {
    navigate(route) {
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}