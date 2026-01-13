package com.contraomnese.courses.features.auth.login.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.domain.models.auth.Email
import com.contraomnese.courses.domain.models.auth.Password
import com.contraomnese.courses.domain.models.auth.toEmail
import com.contraomnese.courses.domain.models.auth.toPassword
import com.contraomnese.courses.presentation.architecture.MviState

@Immutable
internal data class LoginState(
    override val isLoading: Boolean = false,
    val login: Email = Email(""),
    val password: Password = Password(""),
) : MviState {

    fun setLogin(login: String): LoginState = copy(login = login.toEmail())
    fun setPassword(password: String): LoginState = copy(password = password.toPassword())
    fun completeLoading(): LoginState = copy(isLoading = false)
    fun isValid() = login.isValidEmail() && login.value.isNotEmpty() && password.value.isNotEmpty()

    companion object {
        val DEFAULT = LoginState(isLoading = true)
    }
}