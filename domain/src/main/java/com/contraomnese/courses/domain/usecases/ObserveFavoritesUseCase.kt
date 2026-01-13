package com.contraomnese.courses.domain.usecases

import com.contraomnese.courses.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

fun interface ObserveFavoritesUseCase {
    operator fun invoke(): Flow<List<Long>>
}

class ObserveFavoritesUseCaseImpl(
    private val repository: FavoritesRepository,
) : ObserveFavoritesUseCase {
    override fun invoke(): Flow<List<Long>> = repository.observeFavorites()
}