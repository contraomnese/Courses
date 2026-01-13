package com.contraomnese.courses.di

import com.contraomnese.courses.BuildConfig
import com.contraomnese.courses.data.network.api.CoursesApi
import com.contraomnese.courses.data.network.interceptors.ErrorInterceptor
import com.contraomnese.courses.data.network.parsers.ApiParser
import com.contraomnese.courses.data.network.parsers.ApiParserImpl
import com.contraomnese.courses.data.repository.CoursesRepositoryImpl
import com.contraomnese.courses.data.repository.FavoritesRepositoryImpl
import com.contraomnese.courses.data.storage.db.AppDatabase
import com.contraomnese.courses.domain.repository.CoursesRepository
import com.contraomnese.courses.domain.repository.FavoritesRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {

    single<Gson> {
        GsonBuilder().create()
    }

    // region Network
    single {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    factory<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<ErrorInterceptor>())
            .build()
    }

    factory<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.BASIC
            }
        }
    }

    factory<ErrorInterceptor> {
        ErrorInterceptor()
    }

    // API
    single<CoursesApi> { get<Retrofit>().create(CoursesApi::class.java) }
    // endregion

    // region databases
    single<AppDatabase> { AppDatabase.create(context = get()) }
    // endregion

    single<ApiParser>() {
        ApiParserImpl(
            converterFactory = GsonConverterFactory.create(get()),
            retrofit = get<Retrofit>()
        )
    }

    // region Repositories
    single<CoursesRepository> {
        CoursesRepositoryImpl(
            api = get(),
            apiParser = get(),
            database = get(),
            dispatcher = Dispatchers.IO
        )
    }

    single<FavoritesRepository> {
        FavoritesRepositoryImpl(
            database = get(),
            dispatcher = Dispatchers.IO
        )
    }

//    // region Storages
//    single<UserStorage> {
//        UserMemoryStorage(
//            context = get()
//        )
//    }
//    // endregion
}