package com.contraomnese.courses.data.repository

import androidx.room.withTransaction
import com.contraomnese.courses.data.mappers.toDomain
import com.contraomnese.courses.data.mappers.toEntity
import com.contraomnese.courses.data.models.courses.CoursesResponse
import com.contraomnese.courses.data.network.api.CoursesApi
import com.contraomnese.courses.data.network.parsers.ApiParser
import com.contraomnese.courses.data.storage.db.AppDatabase
import com.contraomnese.courses.data.storage.db.courses.entity.FavoriteEntity
import com.contraomnese.courses.domain.exceptions.badRequest
import com.contraomnese.courses.domain.exceptions.logPrefix
import com.contraomnese.courses.domain.exceptions.operationFailed
import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.repository.CoursesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

class CoursesRepositoryImpl(
    private val api: CoursesApi,
    private val apiParser: ApiParser,
    private val database: AppDatabase,
    private val dispatcher: CoroutineDispatcher,
): CoursesRepository {

    override suspend fun getCourses(): Result<List<Course>> {
        val localCourses = database.coursesDao()
            .getCourses()
            .map { it.toDomain() }

        if (localCourses.isNotEmpty()) {
            return Result.success(localCourses)
        }

        return requestCourses()
            .mapCatching { response ->
                val courseEntities = response.courses.toEntity()

                database.withTransaction {
                    database.coursesDao().addCourses(courseEntities)
                    database.favoritesDao().addFavorites(
                        response.courses
                            .filter { it.hasLike }
                            .map { FavoriteEntity(courseId = it.id) }
                    )
                }

                courseEntities.toDomain()
            }
    }

    private suspend fun requestCourses() = withContext(dispatcher) {
        try {
            parseCourses(api.getCourses())
        } catch (cause: Exception) {
            throw badRequest(logPrefix("Courses not found"), cause)
        }
    }

    private fun parseCourses(courses: Response<CoursesResponse>) =
        try {
            Result.success(apiParser.parseOrThrowError(courses))
        } catch (cause: Exception) {
            Result.failure(operationFailed(logPrefix("Impossible parse matching locations from network"), cause))
        }

}