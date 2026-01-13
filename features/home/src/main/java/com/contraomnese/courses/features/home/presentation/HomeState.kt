package com.contraomnese.courses.features.home.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.parsers.DateTimeParser
import com.contraomnese.courses.presentation.architecture.MviState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentSetOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableSet

@Immutable
internal data class HomeState(
    override val isLoading: Boolean = false,
    val courses: ImmutableList<Course> = persistentListOf(),
    val favorites: ImmutableSet<Long> = persistentSetOf(),
) : MviState {

    fun setCourses(courses: List<Course>): HomeState = copy(courses = courses.toImmutableList())
    fun setFavorites(favorites: List<Long>): HomeState = copy(favorites = favorites.toImmutableSet(), isLoading = false)
    fun sortCourses(sortType: CourseSortType): HomeState = copy(
        courses = when (sortType) {
            is CourseSortType.ByPublishDate ->
                courses
                    .sortedByDescending { DateTimeParser.toDate(it.publishDate) }
                    .toImmutableList()
        }
    )
    fun completeLoading(): HomeState = copy(isLoading = false)

    companion object {
        val DEFAULT = HomeState(isLoading = true)
    }
}

sealed interface CourseSortType {
    data object ByPublishDate: CourseSortType
}