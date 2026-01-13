package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviAction

internal sealed interface MainActivityAction : MviAction {
    data object LottieAnimationFinished : MainActivityAction
}