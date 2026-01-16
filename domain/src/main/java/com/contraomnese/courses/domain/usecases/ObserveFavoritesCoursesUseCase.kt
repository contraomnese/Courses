package com.contraomnese.courses.domain.usecases

import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

fun interface ObserveFavoritesCoursesUseCase {
    operator fun invoke(): Flow<List<Course>>
}

class ObserveFavoritesCoursesUseCaseImpl(
    private val repository: FavoritesRepository,
) : ObserveFavoritesCoursesUseCase {
    override fun invoke(): Flow<List<Course>> = repository.observeFavoriteCourses()
}