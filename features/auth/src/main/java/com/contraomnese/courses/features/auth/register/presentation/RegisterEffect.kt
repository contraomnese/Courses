package com.contraomnese.courses.features.auth.register.presentation

import com.contraomnese.courses.presentation.architecture.MviEffect

internal sealed interface RegisterEffect : MviEffect {
    data class LoginChanged(val login: String): RegisterEffect
    data class PasswordChanged(val password: String): RegisterEffect
    data class ConfirmPasswordChanged(val password: String): RegisterEffect
    data object CompleteLoading: RegisterEffect
}