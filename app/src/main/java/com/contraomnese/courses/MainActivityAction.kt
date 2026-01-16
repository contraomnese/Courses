package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviAction
import com.contraomnese.courses.presentation.architecture.MviDestination

internal sealed interface MainActivityAction : MviAction {
    data object AnimationFinished : MainActivityAction
    data class NavigateTo(val destination: MviDestination) : MainActivityAction
}