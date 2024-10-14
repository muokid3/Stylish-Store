package com.dm.berxley.stylishstore.presentation.onboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dm.berxley.stylishstore.domain.repositories.AuthRepository
import com.dm.berxley.stylishstore.domain.sharedprefs.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val localUserManager: LocalUserManager,
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()


    fun login(email: String, password: String) {
        _loginState.update {
            it.copy(isLoading = true)
        }

        //call api here
        viewModelScope.launch {
            authRepository.login(email, password).collectLatest { loginResponse ->

                val responseCode = loginResponse.code()
                if (loginResponse.isSuccessful) {
                    val token = loginResponse.body()?.access_token ?: ""
                    //val refreshToken = loginResponse.body()?.refresh_token

                    localUserManager.saveAccessToken(token)
                    _loginState.update {
                        it.copy(isLoading = false, loginSuccessful = true, errorMessage = "")
                    }


                } else {
                    val httpErrorMessage = loginResponse.message()
                    val errorMessage = "Error $responseCode: $httpErrorMessage"

                    _loginState.update {
                        it.copy(
                            isLoading = false,
                            loginSuccessful = false,
                            errorMessage = errorMessage
                        )
                    }
                }

            }
        }

    }

    fun reset() {
        _loginState.update {
            it.copy(isLoading = false, loginSuccessful = false, errorMessage = "")
        }
    }


}