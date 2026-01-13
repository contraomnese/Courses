package com.contraomnese.courses.data.storage.db.courses.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.contraomnese.courses.data.storage.db.courses.entity.CourseEntity
import com.contraomnese.courses.data.storage.db.courses.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: FavoriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(favorites: List<FavoriteEntity>)

    @Query(
        "INSERT INTO favorites (course_id) " +
                "SELECT course_id " +
                "FROM courses WHERE course_id = :courseId"
    )
    suspend fun addFavorite(courseId: Long)

    @Query("SELECT course_id FROM favorites")
    fun observeFavorites(): Flow<List<Long>>

    @Query("SELECT c.* FROM courses c INNER JOIN favorites f ON f.course_id = c.course_id")
    suspend fun getFavoriteCourses(): List<CourseEntity>

    @Query("SELECT c.* FROM courses c INNER JOIN favorites f ON f.course_id = c.course_id")
    fun observeFavoriteCourses(): Flow<List<CourseEntity>>

    @Query("DELETE FROM favorites WHERE course_id = :courseId")
    suspend fun removeFavorite(courseId: Long)

}