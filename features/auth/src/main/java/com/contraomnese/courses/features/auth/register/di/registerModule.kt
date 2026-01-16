package com.contraomnese.courses.features.auth.register.di

import com.contraomnese.courses.features.auth.register.presentation.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val registerModule = module {

    viewModelOf(::RegisterViewModel)
}