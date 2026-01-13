package com.contraomnese.courses.domain.usecases

import com.contraomnese.courses.domain.repository.FavoritesRepository

fun interface AddFavoriteUseCase {
    suspend operator fun invoke(request: Long): Result<Long>
}

class AddFavoriteUseCaseImpl(
    private val repository: FavoritesRepository,
) : AddFavoriteUseCase {
    override suspend fun invoke(request: Long): Result<Long> = repository.addFavorite(request)
}