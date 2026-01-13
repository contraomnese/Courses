package com.contraomnese.courses.data.storage.db.courses.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = FavoriteEntity.TABLE_NAME,
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = [CourseEntity.COURSE_ID],
        childColumns = [FavoriteEntity.COURSE_ID],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = [FavoriteEntity.COURSE_ID], name = "course_id")]
)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = COURSE_ID) val courseId: Long,
) {
    companion object {
        const val TABLE_NAME = "favorites"
        const val COURSE_ID = "course_id"
    }
}