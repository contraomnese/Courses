package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface MainActivityEffect : MviEffect {
    data object NotLoading : MainActivityEffect
}