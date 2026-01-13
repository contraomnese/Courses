package com.contraomnese.courses.features.home.di

import com.contraomnese.courses.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val homeModule = module {

    viewModelOf(::HomeViewModel)
}