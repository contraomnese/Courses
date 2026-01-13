package com.contraomnese.courses.account.di

import com.contraomnese.courses.account.presentation.AccountViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val accountModule = module {

    viewModelOf(::AccountViewModel)
}