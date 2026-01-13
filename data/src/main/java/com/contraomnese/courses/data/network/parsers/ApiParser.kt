package com.contraomnese.courses.data.network.parsers

import retrofit2.Response

interface ApiParser {
    fun <T : Any> parseOrThrowError(response: Response<T>): T
}