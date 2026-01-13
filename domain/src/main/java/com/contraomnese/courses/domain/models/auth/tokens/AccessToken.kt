package com.contraomnese.courses.domain.models.auth.tokens

data class AccessToken(
    val accessToken: String = "",
    val accessTokenSecret: String = "",
)