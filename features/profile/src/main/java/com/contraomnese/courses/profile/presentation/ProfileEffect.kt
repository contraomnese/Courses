package com.contraomnese.courses.profile.presentation

import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface ProfileEffect : MviEffect {
    data object CompleteLoading: ProfileEffect
}