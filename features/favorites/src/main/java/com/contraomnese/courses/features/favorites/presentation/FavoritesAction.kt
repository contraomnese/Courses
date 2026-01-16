package com.contraomnese.courses.features.favorites.presentation

import com.contraomnese.courses.presentation.architecture.MviAction

internal sealed interface FavoritesAction : MviAction {
    data class AddFavorite(val id: Long) : FavoritesAction
    data class RemoveFavorite(val id: Long) : FavoritesAction
}