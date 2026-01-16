package com.contraomnese.courses.features.auth.login.di

import com.contraomnese.courses.features.auth.login.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val loginModule = module {

    viewModelOf(::LoginViewModel)
}