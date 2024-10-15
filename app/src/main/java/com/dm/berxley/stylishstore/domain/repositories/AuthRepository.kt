package com.dm.berxley.stylishstore.domain.repositories

import com.dm.berxley.stylishstore.data.remote.dtos.LoginDto
import com.dm.berxley.stylishstore.data.remote.dtos.RegisterDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginDto>
    suspend fun register(name: String, email: String, password: String): Response<RegisterDto>

}