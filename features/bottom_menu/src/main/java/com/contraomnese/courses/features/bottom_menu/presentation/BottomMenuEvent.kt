package com.contraomnese.courses.features.bottom_menu.presentation

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface BottomMenuEvent : MviEvent {
    data class HandleError(val cause: Throwable) : BottomMenuEvent
}