package com.contraomnese.courses.account.presentation

import com.contraomnese.courses.presentation.architecture.MviModel

internal class AccountViewModel(
) : MviModel<AccountAction, AccountEffect, AccountEvent, AccountState>(
    defaultState = AccountState.DEFAULT,
    tag = "AccountViewModel",
) {
}

