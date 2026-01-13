package com.contraomnese.courses.data.storage.db.courses.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.contraomnese.courses.data.storage.db.courses.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CoursesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourse(course: CourseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCourses(courses: List<CourseEntity>)

    @Query("SELECT * FROM courses")
    suspend fun getCourses(): List<CourseEntity>

    @Query("SELECT * FROM courses")
    fun observeCourses(): Flow<List<CourseEntity>>
}