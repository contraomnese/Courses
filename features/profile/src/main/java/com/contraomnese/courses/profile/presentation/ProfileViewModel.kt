package com.contraomnese.courses.profile.presentation

import com.contraomnese.courses.presentation.architecture.MviModel

internal class ProfileViewModel(
) : MviModel<ProfileAction, ProfileEffect, ProfileEvent, ProfileState>(
    defaultState = ProfileState.DEFAULT,
    tag = "ProfileViewModel",
) {

    override fun reducer(effect: ProfileEffect, previousState: ProfileState): ProfileState =
        when (effect) {
            ProfileEffect.CompleteLoading -> previousState.completeLoading()
        }
}

