package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviModel

internal class MainActivityViewModel: MviModel<MainActivityAction, MainActivityEffect, MainActivityEvent, MainActivityState>(
    tag = "MainActivity",
    defaultState = MainActivityState.DEFAULT
) {
    override suspend fun bootstrap() {
    }

    override fun reducer(effect: MainActivityEffect, previousState: MainActivityState): MainActivityState = when (effect) {
        is MainActivityEffect.NotLoading -> previousState.copy(isLoading = false)
    }

    override suspend fun actor(action: MainActivityAction) = when (action) {
        is MainActivityAction.LottieAnimationFinished -> push(MainActivityEffect.NotLoading)
    }
}