package com.dm.berxley.stylishstore.presentation.onboarding.login

data class LoginState(
    var isLoading: Boolean = false,
    var loginSuccessful: Boolean = false,
    var errorMessage: String = ""
)
