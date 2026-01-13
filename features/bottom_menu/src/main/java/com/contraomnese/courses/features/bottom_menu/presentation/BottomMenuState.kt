package com.contraomnese.courses.features.bottom_menu.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.core.navigation.TopLevelDestination
import com.contraomnese.courses.presentation.architecture.MviState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
internal data class BottomMenuState(
    override val isLoading: Boolean = false,
    val topLevelDestinations: ImmutableList<TopLevelDestination> = persistentListOf()
) : MviState {

    fun completeLoading(): BottomMenuState = copy(isLoading = false)

    companion object {
        val DEFAULT = BottomMenuState(
            isLoading = false
        )
    }
}