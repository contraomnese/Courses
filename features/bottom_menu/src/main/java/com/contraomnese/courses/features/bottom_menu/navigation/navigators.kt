package com.contraomnese.courses.features.bottom_menu.navigation

import androidx.navigation.NavHostController
import com.contraomnese.courses.features.favorites.navigation.FavoritesNavigator
import com.contraomnese.courses.features.home.navigation.HomeNavigator
import com.contraomnese.courses.profile.navigation.ProfileNavigator

fun NavHostController.accountNavigator(externalNavigator: BottomMenuNavigator): ProfileNavigator = object: ProfileNavigator{

    override fun onNavigateToUp() {
        externalNavigator.onNavigateUp()
    }

}

fun NavHostController.favoritesNavigator(externalNavigator: BottomMenuNavigator): FavoritesNavigator = object: FavoritesNavigator{

    override fun onNavigateToUp() {
        externalNavigator.onNavigateUp()
    }

}

fun NavHostController.homeNavigator(externalNavigator: BottomMenuNavigator): HomeNavigator = object: HomeNavigator{

    override fun onNavigateToUp() {
        externalNavigator.onNavigateUp()
    }

}