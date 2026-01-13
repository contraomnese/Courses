package com.contraomnese.courses

import com.contraomnese.courses.features.bottom_menu.navigation.BottomMenuGraph
import com.contraomnese.courses.presentation.architecture.MviDestination
import com.contraomnese.courses.presentation.architecture.MviState

internal data class MainActivityState(
    override val isLoading: Boolean = true,
    val startDestination: MviDestination,
) : MviState {

    companion object {
        val DEFAULT = MainActivityState(isLoading = true, startDestination = BottomMenuGraph)
    }
}