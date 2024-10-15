package com.dm.berxley.stylishstore.data.repositories

import com.dm.berxley.stylishstore.data.remote.AppApi
import com.dm.berxley.stylishstore.data.remote.dtos.LoginDto
import com.dm.berxley.stylishstore.data.remote.dtos.RegisterDto
import com.dm.berxley.stylishstore.domain.models.payloads.LoginPayload
import com.dm.berxley.stylishstore.domain.models.payloads.RegisterPayload
import com.dm.berxley.stylishstore.domain.repositories.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val appApi: AppApi
) : AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginDto> {
        val loginPayload = LoginPayload(email = email, password = password)
        return appApi.login(loginPayload)
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Response<RegisterDto> {
        val registerPayload = RegisterPayload(name = name, email = email, password = password)
        return appApi.register(registerPayload)
    }
}