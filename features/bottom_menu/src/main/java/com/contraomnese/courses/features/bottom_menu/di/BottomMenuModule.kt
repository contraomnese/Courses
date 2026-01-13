package com.contraomnese.courses.features.bottom_menu.di

import com.contraomnese.courses.account.navigation.AccountTopLevelDestination
import com.contraomnese.courses.core.navigation.TopDestinationsCollection
import com.contraomnese.courses.features.bottom_menu.presentation.BottomMenuViewModel
import com.contraomnese.courses.features.home.navigation.HomeTopLevelDestination
import kotlinx.collections.immutable.persistentListOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val bottomMenuModule = module {

    viewModelOf(::BottomMenuViewModel)

    single<TopDestinationsCollection> { TopDestinationsCollection(
        items = persistentListOf(
            HomeTopLevelDestination(),
            AccountTopLevelDestination(),
        )
    ) }
}