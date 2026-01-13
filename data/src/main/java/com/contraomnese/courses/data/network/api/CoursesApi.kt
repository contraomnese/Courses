package com.contraomnese.courses.data.network.api

import com.contraomnese.courses.data.models.courses.CoursesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoursesApi {

    @GET("u/0/uc")
    suspend fun getCourses(
        @Query("id") id: String = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q",
        @Query("export") export: String = "download"
    ): Response<CoursesResponse>
}
