package com.contraomnese.courses.features.favorites.presentation

import com.contraomnese.courses.domain.usecases.AddFavoriteUseCase
import com.contraomnese.courses.domain.usecases.ObserveFavoritesCoursesUseCase
import com.contraomnese.courses.domain.usecases.RemoveFavoriteUseCase
import com.contraomnese.courses.presentation.architecture.MviModel

internal class FavoritesViewModel(
    private val observeFavoritesCoursesUseCase: ObserveFavoritesCoursesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : MviModel<FavoritesAction, FavoritesEffect, HomeEvent, FavoritesState>(
    defaultState = FavoritesState.DEFAULT,
    tag = "HomeViewModel",
) {

    override suspend fun bootstrap() {
        observeFavoritesCoursesUseCase()
            .collect { favorites ->
                push(FavoritesEffect.FavoritesChanged(favorites))
            }
    }

    override fun reducer(effect: FavoritesEffect, previousState: FavoritesState) =
        when (effect) {
            is FavoritesEffect.CompleteLoading -> previousState.completeLoading()
            is FavoritesEffect.FavoritesChanged -> previousState.setFavorites(effect.favorites)
        }

    override suspend fun actor(action: FavoritesAction) = when (action) {
        is FavoritesAction.AddFavorite -> processFavoriteAdd(action.id)
        is FavoritesAction.RemoveFavorite -> processFavoriteRemove(action.id)
    }

    private suspend fun processFavoriteAdd(id: Long) {
        addFavoriteUseCase(id)
            .onFailure { push(HomeEvent.HandleError(it)) }
    }

    private suspend fun processFavoriteRemove(id: Long) {
        removeFavoriteUseCase(id)
            .onFailure {
                push(HomeEvent.HandleError(it))
            }
    }

}

