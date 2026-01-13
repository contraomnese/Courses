package com.contraomnese.courses.account.presentation

import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface AccountEffect : MviEffect {
    data object CompleteLoading: AccountEffect
}