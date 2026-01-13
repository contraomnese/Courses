package com.contraomnese.courses.domain.usecases

import com.contraomnese.courses.domain.models.Course
import com.contraomnese.courses.domain.repository.FavoritesRepository

fun interface GetFavoritesUseCase {
    suspend operator fun invoke(): Result<List<Course>>
}

class GetFavoritesUseCaseImpl(
    private val repository: FavoritesRepository,
) : GetFavoritesUseCase {

    override suspend fun invoke(): Result<List<Course>> =
        repository.getFavorites()

}