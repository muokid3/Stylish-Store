package com.dm.berxley.stylishstore.presentation.onboarding.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dm.berxley.stylishstore.domain.repositories.AuthRepository
import com.dm.berxley.stylishstore.domain.sharedprefs.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val localUserManager: LocalUserManager,
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()


    suspend fun login(email: String, password: String) {
        _loginState.update {
            it.copy(isLoading = true)
        }

        //call api here
        val loginResponse = authRepository.login(email, password)

        val responseCode = loginResponse.code()
        if (loginResponse.isSuccessful) {
            val token = loginResponse.body()?.access_token ?: ""
            //val refreshToken = loginResponse.body()?.refresh_token ?: ""

            localUserManager.saveAccessToken(token)
            localUserManager.saveIsLoggedIn(true)
            _loginState.update {
                it.copy(isLoading = false, loginSuccessful = true, errorMessage = "")
            }


        } else {
            val errorBody = loginResponse.errorBody()?.string()
            if (errorBody != null) {
                Log.e("Error:", errorBody)
            }
            val message = try {
                if (errorBody != null) {
                    JSONObject(errorBody).get("message")
                } else {
                    "Unknown API Error"
                }
            } catch (e: JSONException) {
                "Fatal API Error"
            }
            val errorMessage = "Error $responseCode: $message"

            _loginState.update {
                it.copy(
                    isLoading = false,
                    loginSuccessful = false,
                    errorMessage = errorMessage
                )
            }
        }

    }

    fun reset() {
        _loginState.update {
            it.copy(isLoading = false, loginSuccessful = false, errorMessage = "")
        }
    }


}