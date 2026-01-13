package com.contraomnese.courses.data.repository

import com.contraomnese.courses.data.mappers.toDomain
import com.contraomnese.courses.data.storage.db.AppDatabase
import com.contraomnese.courses.data.storage.db.courses.entity.CourseEntity
import com.contraomnese.courses.domain.exceptions.logPrefix
import com.contraomnese.courses.domain.exceptions.storageError
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FavoritesRepositoryImpl(
    private val database: AppDatabase,
    private val dispatcher: CoroutineDispatcher,
) : FavoritesRepository {

    override suspend fun addFavorite(id: Long): Result<Long> = withContext(dispatcher) {

        try {
            database.favoritesDao().addFavorite(id)
            Result.success(id)
        } catch (cause: Exception) {
            Result.failure(storageError(logPrefix("Impossible add favorite to storage"), cause))
        }
    }

    override suspend fun deleteFavorite(id: Long): Result<Long> = withContext(dispatcher) {
        try {
            database.favoritesDao().removeFavorite(id)
            Result.success(id)
        } catch (cause: Exception) {
            Result.failure(storageError(logPrefix("Impossible remove favorite from storage"), cause))
        }
    }

    override suspend fun getFavorites(): Result<List<Course>> =
        getFavoritesResult()
            .mapCatching { entities -> entities.toDomain() }

    override fun observeFavorites(): Flow<List<Long>> =
        database
            .favoritesDao()
            .observeFavorites()
            .flowOn(dispatcher)

    override fun observeFavoriteCourses(): Flow<List<Course>> =
        database
            .favoritesDao()
            .observeFavoriteCourses()
            .map { favorites -> favorites.map { it.toDomain() } }
            .flowOn(dispatcher)

    private suspend fun getFavoritesResult(): Result<List<CourseEntity>> =
        withContext(dispatcher) {
            try {
                Result.success(database.favoritesDao().getFavoriteCourses())
            } catch (cause: Exception) {
                Result.failure(storageError(logPrefix("Impossible get favorites from storage"), cause))
            }
        }
}