package com.contraomnese.courses.features.home.presentation

import com.contraomnese.courses.domain.usecases.AddFavoriteUseCase
import com.contraomnese.courses.domain.usecases.GetCoursesUseCase
import com.contraomnese.courses.domain.usecases.ObserveFavoritesUseCase
import com.contraomnese.courses.domain.usecases.RemoveFavoriteUseCase
import com.contraomnese.courses.presentation.architecture.MviModel
import kotlinx.coroutines.delay

internal class HomeViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
) : MviModel<HomeAction, HomeEffect, HomeEvent, HomeState>(
    defaultState = HomeState.DEFAULT,
    tag = "HomeViewModel",
) {

    override suspend fun bootstrap() {
        getCoursesUseCase()
            .onSuccess {
                push(HomeEffect.CoursesChanged(it))
                delay(100)
                observeFavoritesUseCase()
                    .collect { favorites ->
                        push(HomeEffect.FavoritesChanged(favorites))
                    }
            }
            .onFailure {
                push(HomeEvent.HandleError(it))
            }
    }

    override fun reducer(effect: HomeEffect, previousState: HomeState) =
        when (effect) {
            is HomeEffect.CoursesChanged -> previousState.setCourses(effect.courses)
            is HomeEffect.CompleteLoading -> previousState.completeLoading()
            is HomeEffect.SortCourses -> previousState.sortCourses(effect.sortType)
            is HomeEffect.FavoritesChanged -> previousState.setFavorites(effect.favorites)
        }

    override suspend fun actor(action: HomeAction) = when (action) {
        HomeAction.SortByPublishDate -> push(HomeEffect.SortCourses(CourseSortType.ByPublishDate))
        is HomeAction.AddFavorite -> processFavoriteAdd(action.id)
        is HomeAction.RemoveFavorite -> processFavoriteRemove(action.id)
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

