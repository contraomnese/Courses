package com.contraomnese.courses.features.home.presentation

import com.contraomnese.courses.presentation.architecture.MviAction

internal sealed interface HomeAction : MviAction {
    data object SortByPublishDate: HomeAction
    data class AddFavorite(val id: Long) : HomeAction
    data class RemoveFavorite(val id: Long) : HomeAction
}