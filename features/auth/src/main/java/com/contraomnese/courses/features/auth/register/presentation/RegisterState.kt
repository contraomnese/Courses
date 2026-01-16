package com.contraomnese.courses.features.auth.register.presentation

import androidx.compose.runtime.Immutable
import com.contraomnese.courses.domain.models.auth.Email
import com.contraomnese.courses.domain.models.auth.Password
import com.contraomnese.courses.domain.models.auth.toEmail
import com.contraomnese.courses.domain.models.auth.toPassword
import com.contraomnese.courses.presentation.architecture.MviState

@Immutable
internal data class RegisterState(
    override val isLoading: Boolean = false,
    val login: Email = Email(""),
    val password: Password = Password(""),
    val confirmPassword: Password = Password(""),
) : MviState {

    fun setLogin(login: String): RegisterState = copy(login = login.toEmail())
    fun setPassword(password: String): RegisterState = copy(password = password.toPassword())
    fun setConfirmPassword(password: String): RegisterState = copy(confirmPassword = password.toPassword())
    fun completeLoading(): RegisterState = copy(isLoading = false)
    fun isValid() = login.isValidEmail() && login.value.isNotEmpty() && password.value.isNotEmpty() && password.value == confirmPassword.value

    companion object {
        val DEFAULT = RegisterState(isLoading = true)
    }
}