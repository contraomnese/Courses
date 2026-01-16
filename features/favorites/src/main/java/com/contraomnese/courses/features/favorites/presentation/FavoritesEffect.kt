package com.contraomnese.courses.features.favorites.presentation

import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface FavoritesEffect : MviEffect {
    data class FavoritesChanged(val favorites: List<Course>): FavoritesEffect
    data object CompleteLoading: FavoritesEffect
}