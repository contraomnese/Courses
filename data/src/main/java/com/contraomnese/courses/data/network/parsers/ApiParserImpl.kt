package com.contraomnese.courses.data.network.parsers

import com.contraomnese.courses.domain.exceptions.DomainException
import com.contraomnese.courses.domain.exceptions.badRequest
import com.contraomnese.courses.domain.exceptions.notFound
import com.contraomnese.courses.domain.exceptions.unauthorized
import com.contraomnese.courses.domain.exceptions.unknown
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

class ApiParserImpl(
    private val converterFactory: Converter.Factory,
    private val retrofit: Retrofit,
) : ApiParser {

    override fun <T : Any> parseOrThrowError(response: Response<T>): T {
        if (response.isSuccessful) {
            val body = response.body()
                ?: throw IllegalStateException("Response body is null")
            return body
        }
        else throw mapError(response)
    }

    private fun <T : Any> mapError(response: Response<T>): DomainException {
        return when (response.code()) {

            401, 403 -> unauthorized(response.message() ?: "Unauthorized")

            404 -> notFound(response.message() ?: "Not Found")

            in 500..599 -> badRequest(response.message() ?: "Bad request")

            else -> unknown(response.message() ?: "Unknown error")
        }
    }
}