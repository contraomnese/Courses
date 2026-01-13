package com.contraomnese.courses.navigation

import androidx.navigation.NavHostController
import com.contraomnese.courses.features.auth.navigation.AuthGraph
import com.contraomnese.courses.features.auth.navigation.AuthNavigator
import com.contraomnese.courses.features.bottom_menu.navigation.BottomMenuNavigator
import com.contraomnese.courses.features.home.navigation.HomeNavigator


fun NavHostController.authNavigator(): AuthNavigator = object : AuthNavigator {
    override fun onNavigateToLogin() {
        popBackStack()
        navigate(AuthGraph)
    }

    override fun onNavigateToRegister() {
        TODO("Not yet implemented")
    }
}

fun NavHostController.homeNavigator() = object : HomeNavigator {

    override fun onNavigateToHome() {
        TODO("Not yet implemented")
    }
}

fun NavHostController.bottomMenuNavigator(): BottomMenuNavigator = object : BottomMenuNavigator {

    override fun onLogOut() {
        popBackStack()
        navigate(AuthGraph)
    }

    override fun onNavigateUp() {
        popBackStack()
    }
}