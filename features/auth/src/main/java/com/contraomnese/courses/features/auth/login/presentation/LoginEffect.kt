package com.contraomnese.courses.features.auth.login.presentation

import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface LoginEffect : MviEffect {
    data class LoginChanged(val login: String): LoginEffect
    data class PasswordChanged(val password: String): LoginEffect
    data object CompleteLoading: LoginEffect
}