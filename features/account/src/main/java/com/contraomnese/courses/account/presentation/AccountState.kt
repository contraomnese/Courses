package com.contraomnese.courses.account.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.presentation.architecture.MviState

@Immutable
internal data class AccountState(
    override val isLoading: Boolean = false,
) : MviState {


    fun completeLoading(): AccountState = copy(isLoading = false)

    companion object {
        val DEFAULT = AccountState(isLoading = true)
    }
}