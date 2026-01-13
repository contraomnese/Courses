package com.contraomnese.courses.domain.models

data class Course(
    val id: Long,
    val title: String,
    val description: String,
    val price: String,
    val rate: Float,
    val startDate: String,
    val publishDate: String,
)
