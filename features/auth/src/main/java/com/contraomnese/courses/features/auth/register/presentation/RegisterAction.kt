package com.contraomnese.courses.features.auth.register.presentation

import com.contraomnese.courses.presentation.architecture.MviAction

internal sealed interface RegisterAction : MviAction {
    data class LoginChange(val login: String): RegisterAction
    data class PasswordChange(val password: String): RegisterAction
    data class ConfirmPasswordChange(val password: String): RegisterAction
}