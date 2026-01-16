package com.contraomnese.courses.features.favorites.di

import com.contraomnese.courses.features.favorites.presentation.FavoritesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val favoritesModule = module {

    viewModelOf(::FavoritesViewModel)
}