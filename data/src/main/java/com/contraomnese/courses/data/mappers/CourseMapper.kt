package com.contraomnese.courses.data.mappers

import com.contraomnese.courses.data.models.courses.CourseNetwork
import com.contraomnese.courses.data.storage.db.courses.entity.CourseEntity
import com.contraomnese.courses.domain.exceptions.logPrefix
import com.contraomnese.courses.domain.exceptions.operationFailed
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.parsers.DateTimeParser

fun CourseNetwork.toEntity() = CourseEntity(
    courseId = id,
    title = title,
    description = text,
    price = price,
    rate = rate.trim().toFloat(),
    startDate = startDate,
    publishDate = publishDate,
)

fun CourseEntity.toDomain() = Course(
    id = courseId,
    title = title,
    description = description,
    price = price,
    rate = rate,
    startDate = startDate,
    publishDate = DateTimeParser.parseIso(publishDate) ?: publishDate,
)

fun List<CourseNetwork>.toEntity() = try {
    this.map { it.toEntity() }
} catch (cause: Exception) {
    throw operationFailed(logPrefix("Impossible convert courses from network"), cause)
}

fun List<CourseEntity>.toDomain() = try {
    this.map { it.toDomain() }
} catch (cause: Exception) {
    throw operationFailed(logPrefix("Impossible convert courses from database"), cause)
}