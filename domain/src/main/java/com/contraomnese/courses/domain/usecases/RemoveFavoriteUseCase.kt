package com.contraomnese.courses.domain.usecases

import com.contraomnese.courses.domain.repository.FavoritesRepository


fun interface RemoveFavoriteUseCase {
    suspend operator fun invoke(request: Long): Result<Long>
}

class RemoveFavoriteUseCaseImpl(
    private val repository: FavoritesRepository,
) : RemoveFavoriteUseCase {
    override suspend fun invoke(request: Long): Result<Long> = repository.deleteFavorite(request)
}