package com.contraomnese.courses.features.bottom_menu.presentation

import com.contraomnese.courses.core.navigation.TopDestinationsCollection
import com.contraomnese.courses.presentation.architecture.MviModel


internal class BottomMenuViewModel(
    private val topLevelDestinations: TopDestinationsCollection,
) : MviModel<BottomMenuAction, BottomMenuEffect, BottomMenuEvent, BottomMenuState>(
    defaultState = BottomMenuState.DEFAULT.copy(
        topLevelDestinations = topLevelDestinations.items
    ),
    tag = "BottomMenuViewModel"
)