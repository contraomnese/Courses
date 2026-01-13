package com.contraomnese.courses.features.interests.di

import com.contraomnese.courses.features.interests.presentation.InterestsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val interestsModule = module {

    viewModelOf(::InterestsViewModel)
}