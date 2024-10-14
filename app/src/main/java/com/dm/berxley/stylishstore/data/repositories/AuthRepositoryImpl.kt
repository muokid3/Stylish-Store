package com.dm.berxley.stylishstore.data.repositories

import com.dm.berxley.stylishstore.data.remote.AppApi
import com.dm.berxley.stylishstore.data.remote.dtos.LoginDto
import com.dm.berxley.stylishstore.data.remote.dtos.RegisterDto
import com.dm.berxley.stylishstore.domain.models.payloads.LoginPayload
import com.dm.berxley.stylishstore.domain.models.payloads.RegisterPayload
import com.dm.berxley.stylishstore.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val appApi: AppApi
) : AuthRepository {
    override suspend fun login(email: String, password: String): Flow<Response<LoginDto>> {
        return flow {
            val loginPayload = LoginPayload(email = email, password = password)

            val resp = appApi.login(loginPayload)
            emit(resp)
            return@flow
        }
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String,
        avatar: String
    ): Flow<Response<RegisterDto>> {
        return flow {
            val registerPayload =
                RegisterPayload(name = name, email = email, password = password, avatar = avatar)

            val resp = appApi.register(registerPayload)
            emit(resp)
            return@flow
        }
    }
}