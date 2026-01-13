package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface MainActivityEvent : MviEvent {
    data class HandleError(val cause: Throwable) : MainActivityEvent
}