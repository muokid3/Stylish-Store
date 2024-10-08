package com.dm.berxley.stylishstore.domain.sharedprefs

import kotlinx.coroutines.flow.Flow


interface LocalUserManager {
    suspend fun saveIsLoggedIn(isLoggedIn: Boolean)
    suspend fun readIsLoggedIn(): Flow<Boolean>
}