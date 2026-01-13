package com.contraomnese.courses.features.auth.login.presentation

import com.contraomnese.courses.presentation.architecture.MviModel

internal class LoginViewModel : MviModel<LoginAction, LoginEffect, LoginEvent, LoginState>(
    defaultState = LoginState.DEFAULT,
    tag = "LoginViewModel"
) {

    override suspend fun bootstrap() {
        push(LoginEffect.CompleteLoading)
    }

    override fun reducer(effect: LoginEffect, previousState: LoginState) =
        when (effect) {
            is LoginEffect.LoginChanged -> previousState.setLogin(effect.login)
            is LoginEffect.PasswordChanged -> previousState.setPassword(effect.password)
            is LoginEffect.CompleteLoading -> previousState.completeLoading()
        }

    override suspend fun actor(action: LoginAction) = when (action) {
        is LoginAction.LoginChange -> push(LoginEffect.LoginChanged(action.login))
        is LoginAction.PasswordChange -> push(LoginEffect.PasswordChanged(action.password))
    }
}