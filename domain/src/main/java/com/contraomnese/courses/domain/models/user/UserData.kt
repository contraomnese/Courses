package com.contraomnese.courses.domain.models.user

import com.contraomnese.courses.domain.models.auth.AuthState

data class UserData(
    val authState: AuthState
)