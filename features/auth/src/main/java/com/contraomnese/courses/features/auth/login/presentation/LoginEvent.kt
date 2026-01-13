package com.contraomnese.courses.features.auth.login.presentation

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface LoginEvent : MviEvent {
    data class HandleError(val cause: Throwable) : LoginEvent
}