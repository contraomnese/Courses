package com.contraomnese.courses.domain.usecases

import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.repository.CoursesRepository

fun interface GetCoursesUseCase {
    suspend operator fun invoke(): Result<List<Course>>
}

class GetCoursesUseCaseImpl(
    private val repository: CoursesRepository,
) : GetCoursesUseCase {

    override suspend fun invoke(): Result<List<Course>> =
        repository.getCourses()

}