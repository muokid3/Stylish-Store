package com.dm.berxley.stylishstore.domain.sharedprefs

import com.dm.berxley.stylishstore.domain.models.User
import kotlinx.coroutines.flow.Flow


interface LocalUserManager {
    suspend fun saveIsLoggedIn(isLoggedIn: Boolean)
    suspend fun readIsLoggedIn(): Flow<Boolean>

    suspend fun saveAccessToken(accessToken: String)
    suspend fun readAccessToken(): Flow<String>

    suspend fun saveLoggedInUser(user: User)
    suspend fun readLoggedInUser(): Flow<User>
}