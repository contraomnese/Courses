package com.contraomnese.courses

import com.contraomnese.courses.features.auth.navigation.AuthGraph
import com.contraomnese.courses.presentation.architecture.MviDestination
import com.contraomnese.courses.presentation.architecture.MviState

internal data class MainActivityState(
    override val isLoading: Boolean = true,
    val startDestination: MviDestination,
) : MviState {

    fun setStartDestination(destination: MviDestination): MainActivityState = copy(startDestination = destination)

    companion object {
        val DEFAULT = MainActivityState(isLoading = true, startDestination = AuthGraph)
    }
}