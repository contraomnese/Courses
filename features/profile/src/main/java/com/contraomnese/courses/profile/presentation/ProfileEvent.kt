package com.contraomnese.courses.profile.presentation

import com.contraomnese.courses.presentation.architecture.MviEvent

internal sealed interface ProfileEvent : MviEvent {
    data class HandleError(val cause: Throwable) : ProfileEvent
}