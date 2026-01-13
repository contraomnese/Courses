package com.contraomnese.courses.features.home.presentation

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface HomeEvent : MviEvent {
    data class HandleError(val cause: Throwable) : HomeEvent
}