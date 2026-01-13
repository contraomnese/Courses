package com.contraomnese.courses.data.models.courses

import kotlinx.serialization.Serializable

@Serializable
data class CoursesResponse(
    val courses: List<CourseNetwork>
)