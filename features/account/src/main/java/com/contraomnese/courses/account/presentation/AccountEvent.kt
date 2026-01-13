package com.contraomnese.courses.account.presentation

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface AccountEvent : MviEvent {
    data class HandleError(val cause: Throwable) : AccountEvent
}