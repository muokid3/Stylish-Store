package com.dm.berxley.stylishstore.presentation.onboarding.register

data class RegisterState(
    var isLoading: Boolean = false,
    var registerSuccessful: Boolean = false,
    var errorMessage: String = "",



    val nameError: String = "",
    val emailError: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = "",
)