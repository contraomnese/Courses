package com.contraomnese.courses.features.auth.register.presentation

import com.contraomnese.courses.presentation.architecture.MviModel

internal class RegisterViewModel : MviModel<RegisterAction, RegisterEffect, RegisterEvent, RegisterState>(
    defaultState = RegisterState.DEFAULT,
    tag = "LoginViewModel"
) {

    override suspend fun bootstrap() {
        push(RegisterEffect.CompleteLoading)
    }

    override fun reducer(effect: RegisterEffect, previousState: RegisterState) =
        when (effect) {
            is RegisterEffect.LoginChanged -> previousState.setLogin(effect.login)
            is RegisterEffect.PasswordChanged -> previousState.setPassword(effect.password)
            is RegisterEffect.CompleteLoading -> previousState.completeLoading()
            is RegisterEffect.ConfirmPasswordChanged -> previousState.setConfirmPassword(effect.password)
        }

    override suspend fun actor(action: RegisterAction) = when (action) {
        is RegisterAction.LoginChange -> push(RegisterEffect.LoginChanged(action.login))
        is RegisterAction.PasswordChange -> push(RegisterEffect.PasswordChanged(action.password))
        is RegisterAction.ConfirmPasswordChange -> push(RegisterEffect.ConfirmPasswordChanged(action.password))
    }
}