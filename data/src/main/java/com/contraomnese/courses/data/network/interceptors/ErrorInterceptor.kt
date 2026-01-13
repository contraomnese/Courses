package com.contraomnese.courses.data.network.interceptors

import com.contraomnese.courses.data.exceptions.AppException
import okhttp3.Interceptor
import okhttp3.Response

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (!response.isSuccessful) {
            throw AppException(response.message)
        }
        return response
    }
}