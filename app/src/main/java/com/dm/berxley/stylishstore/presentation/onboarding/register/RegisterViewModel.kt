package com.dm.berxley.stylishstore.presentation.onboarding.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.dm.berxley.stylishstore.domain.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) :
    ViewModel() {
    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    suspend fun register(name: String, email: String, password: String, confirmPassword: String) {
        if (validate(name, email, password, confirmPassword)) {
            _registerState.update {
                it.copy(isLoading = true)
            }

            val registerResponse =
                authRepository.register(name = name, email = email, password = password)

            if (registerResponse.isSuccessful) {
                _registerState.update {
                    it.copy(isLoading = false, registerSuccessful = true)
                }
            } else {
                val errorBody = registerResponse.errorBody()?.string()
                var error = "Registration error."
                var message = " Unable to communicate to the server."
                errorBody?.let {
                    error = JSONObject(it).get("error").toString()
                    message = JSONArray(JSONObject(it).get("message").toString()).toString()
                        .replace("[", "")
                        .replace("]", "")
                        .split(",")
                        .joinToString("\n")
                }

                val responseBody = "$error $message"
                _registerState.update {
                    it.copy(isLoading = false, errorMessage = responseBody)
                }
            }
        }
    }

    private fun validate(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        //reset errors if any
        _registerState.update {
            it.copy(nameError = "", emailError = "", passwordError = "", confirmPasswordError = "")
        }

        //validate input
        if (name.isEmpty()) {
            _registerState.update {
                it.copy(nameError = "Please enter your full name")
            }
            return false
        }
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _registerState.update {
                it.copy(emailError = "Please enter a valid E-Mail")
            }
            return false
        }

        if (password.isEmpty()) {
            _registerState.update {
                it.copy(passwordError = "Please enter a password")
            }
            return false
        }

        if (password.length < 4) {
            _registerState.update {
                it.copy(passwordError = "Password must have 4 or more characters")
            }
            return false
        }


        if (password != confirmPassword) {
            _registerState.update {
                it.copy(
                    passwordError = "Both passwords must match",
                    confirmPasswordError = "Both passwords must match"
                )
            }
            return false
        }

        return true
    }
}