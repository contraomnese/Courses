package com.contraomnese.courses.features.auth.register.presentation

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface RegisterEvent : MviEvent {
    data class HandleError(val cause: Throwable) : RegisterEvent
}