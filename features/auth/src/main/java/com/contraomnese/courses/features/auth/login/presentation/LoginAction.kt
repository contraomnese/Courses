package com.contraomnese.courses.features.auth.login.presentation

import com.contraomnese.courses.presentation.architecture.MviAction

internal sealed interface LoginAction : MviAction {
    data class LoginChange(val login: String): LoginAction
    data class PasswordChange(val password: String): LoginAction
}