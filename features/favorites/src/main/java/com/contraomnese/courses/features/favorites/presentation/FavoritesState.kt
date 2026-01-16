package com.contraomnese.courses.features.favorites.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.presentation.architecture.MviState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Immutable
internal data class FavoritesState(
    override val isLoading: Boolean = false,
    val favorites: ImmutableList<Course> = persistentListOf(),
) : MviState {

    fun setFavorites(favorites: List<Course>): FavoritesState = copy(favorites = favorites.toImmutableList(), isLoading = false)
    fun completeLoading(): FavoritesState = copy(isLoading = false)

    companion object {
        val DEFAULT = FavoritesState(isLoading = true)
    }
}