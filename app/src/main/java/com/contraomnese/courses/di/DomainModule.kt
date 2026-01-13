package com.contraomnese.courses.di

import com.contraomnese.courses.domain.usecases.AddFavoriteUseCase
import com.contraomnese.courses.domain.usecases.AddFavoriteUseCaseImpl
import com.contraomnese.courses.domain.usecases.GetCoursesUseCase
import com.contraomnese.courses.domain.usecases.GetCoursesUseCaseImpl
import com.contraomnese.courses.domain.usecases.GetFavoritesUseCase
import com.contraomnese.courses.domain.usecases.GetFavoritesUseCaseImpl
import com.contraomnese.courses.domain.usecases.ObserveFavoritesUseCase
import com.contraomnese.courses.domain.usecases.ObserveFavoritesUseCaseImpl
import com.contraomnese.courses.domain.usecases.RemoveFavoriteUseCase
import com.contraomnese.courses.domain.usecases.RemoveFavoriteUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetCoursesUseCase> {
        GetCoursesUseCaseImpl(
            repository = get()
        )
    }
    factory<ObserveFavoritesUseCase> {
        ObserveFavoritesUseCaseImpl(
            repository = get()
        )
    }
    factory<GetFavoritesUseCase> {
        GetFavoritesUseCaseImpl(
            repository = get()
        )
    }
    factory<AddFavoriteUseCase> {
        AddFavoriteUseCaseImpl(
            repository = get()
        )
    }
    factory<RemoveFavoriteUseCase> {
        RemoveFavoriteUseCaseImpl(
            repository = get()
        )
    }

}
