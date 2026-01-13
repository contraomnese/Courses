package com.contraomnese.courses.data.storage.db.courses.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = CourseEntity.TABLE_NAME
)
data class CourseEntity(
    @PrimaryKey
    @ColumnInfo(name = COURSE_ID)
    val courseId: Long,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = DESCRIPTION) val description: String,
    @ColumnInfo(name = PRICE) val price: String,
    @ColumnInfo(name = RATE) val rate: Float,
    @ColumnInfo(name = START_DATE) val startDate: String,
    @ColumnInfo(name = PUBLISH_DATE) val publishDate: String,
) {
    companion object {
        const val TABLE_NAME = "courses"
        const val COURSE_ID = "course_id"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val PRICE = "price"
        const val RATE = "rate"
        const val START_DATE = "start_date"
        const val PUBLISH_DATE = "publish_date"
    }
}