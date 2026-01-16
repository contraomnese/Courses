package com.contraomnese.courses.navigation

import androidx.navigation.NavHostController
import com.contraomnese.courses.features.auth.login.navigation.navigateToLogin
import com.contraomnese.courses.features.auth.navigation.AuthNavigator
import com.contraomnese.courses.features.auth.navigation.navigateToAuth
import com.contraomnese.courses.features.auth.register.navigation.navigateToRegister
import com.contraomnese.courses.features.bottom_menu.navigation.BottomMenuNavigator
import com.contraomnese.courses.features.bottom_menu.navigation.navigateToBottomMenu

fun NavHostController.authNavigator(): AuthNavigator = object : AuthNavigator {
    override fun onNavigateToLogin() {
        popBackStack()
        navigateToLogin()
    }

    override fun onNavigateToRegister() {
        popBackStack()
        navigateToRegister()
    }

    override fun onNavigateToHome() {
        popBackStack()
        navigateToBottomMenu()
    }
}

fun NavHostController.bottomMenuNavigator(): BottomMenuNavigator = object : BottomMenuNavigator {

    override fun onLogOut() {
        popBackStack()
        navigateToAuth()
    }

    override fun onNavigateUp() {
        popBackStack()
    }
}