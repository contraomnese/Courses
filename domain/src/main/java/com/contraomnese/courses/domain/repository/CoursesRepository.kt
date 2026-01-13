package com.contraomnese.courses.domain.repository

import com.contraomnese.courses.domain.models.Course

interface CoursesRepository {

    suspend fun getCourses(): Result<List<Course>>
}