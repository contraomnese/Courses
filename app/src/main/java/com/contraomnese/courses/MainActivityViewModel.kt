package com.contraomnese.courses

import com.contraomnese.courses.presentation.architecture.MviModel

internal class MainActivityViewModel: MviModel<MainActivityAction, MainActivityEffect, MainActivityEvent, MainActivityState>(
    tag = "MainActivity",
    defaultState = MainActivityState.DEFAULT
) {

    override fun reducer(effect: MainActivityEffect, previousState: MainActivityState): MainActivityState = when (effect) {
        is MainActivityEffect.NotLoading -> previousState.copy(isLoading = false)
        is MainActivityEffect.ChangeStartDestination -> previousState.setStartDestination(effect.destination)
    }



    override suspend fun actor(action: MainActivityAction) = when (action) {
        is MainActivityAction.AnimationFinished -> push(MainActivityEffect.NotLoading)
        is MainActivityAction.NavigateTo -> push(MainActivityEffect.ChangeStartDestination(action.destination))
    }
}