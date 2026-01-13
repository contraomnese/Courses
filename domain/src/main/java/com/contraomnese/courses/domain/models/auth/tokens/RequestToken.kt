package com.contraomnese.courses.domain.models.auth.tokens

data class RequestToken(
    val token: String,
    val secret: String,
)