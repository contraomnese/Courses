package com.contraomnese.courses.profile.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.presentation.architecture.MviState

@Immutable
internal data class ProfileState(
    override val isLoading: Boolean = false,
) : MviState {

    fun completeLoading(): ProfileState = copy(isLoading = false)

    companion object {
        val DEFAULT = ProfileState(isLoading = false)
    }
}