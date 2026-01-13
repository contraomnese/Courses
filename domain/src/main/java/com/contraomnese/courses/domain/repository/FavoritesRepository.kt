package com.contraomnese.courses.domain.repository

import com.contraomnese.courses.domain.models.Course
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun addFavorite(id: Long): Result<Long>
    suspend fun deleteFavorite(id: Long): Result<Long>
    suspend fun getFavorites(): Result<List<Course>>
    fun observeFavorites(): Flow<List<Long>>
    fun observeFavoriteCourses(): Flow<List<Course>>
}