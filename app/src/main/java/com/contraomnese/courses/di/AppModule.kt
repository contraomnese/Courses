package com.contraomnese.courses.di

import com.contraomnese.courses.MainActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::MainActivityViewModel)
}