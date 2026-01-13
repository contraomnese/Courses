package com.contraomnese.courses.features.home.presentation

import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface HomeEffect : MviEffect {
    data class SortCourses(val sortType: CourseSortType): HomeEffect
    data class CoursesChanged(val courses: List<Course>): HomeEffect
    data class FavoritesChanged(val favorites: List<Long>): HomeEffect
    data object CompleteLoading: HomeEffect
}