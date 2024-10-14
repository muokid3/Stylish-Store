package com.dm.berxley.stylishstore.data.remote

import com.dm.berxley.stylishstore.Constants
import com.dm.berxley.stylishstore.data.remote.dtos.LoginDto
import com.dm.berxley.stylishstore.data.remote.dtos.RegisterDto
import com.dm.berxley.stylishstore.domain.models.payloads.LoginPayload
import com.dm.berxley.stylishstore.domain.models.payloads.RegisterPayload
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AppApi {
    //endpoints here

    @POST("auth/login/")
    @Headers("Content-Type: application/json")
    suspend fun login(@Body loginPayload: LoginPayload): Response<LoginDto>

    @POST("users/")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body registerPayload: RegisterPayload): Response<RegisterDto>


    companion object {
        const val BASE_URL = Constants.BASE_URL
    }
}