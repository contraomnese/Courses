package com.contraomnese.courses.features.bottom_menu.presentation

import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface BottomMenuEffect : MviEffect {
    data object CompleteLoading: BottomMenuEffect
}