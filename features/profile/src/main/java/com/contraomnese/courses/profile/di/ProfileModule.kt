package com.contraomnese.courses.profile.di

import com.contraomnese.courses.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val profileModule = module {

    viewModelOf(::ProfileViewModel)
}