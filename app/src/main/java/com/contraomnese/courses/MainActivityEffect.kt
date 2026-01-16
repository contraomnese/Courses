package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviDestination
import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface MainActivityEffect : MviEffect {
    data object NotLoading : MainActivityEffect
    data class ChangeStartDestination(val destination: MviDestination) : MainActivityEffect
}