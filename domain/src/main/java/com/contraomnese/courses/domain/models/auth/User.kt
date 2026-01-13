package com.contraomnese.courses.domain.models.auth

import com.contraomnese.courses.domain.models.auth.tokens.AccessToken

data class User(
    val id: String,
    val username: String,
    val fullname: String,
    val token: AccessToken?
)