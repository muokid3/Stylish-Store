package com.dm.berxley.stylishstore.data.sharedprefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dm.berxley.stylishstore.domain.models.User
import com.dm.berxley.stylishstore.domain.sharedprefs.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {
    override suspend fun saveIsLoggedIn(isLoggedIn: Boolean) {
        context.datastore.edit { prefs ->
            prefs[SharedPrefsKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun readIsLoggedIn(): Flow<Boolean> {
        return context.datastore.data.map { prefs ->
            prefs[SharedPrefsKeys.IS_LOGGED_IN] ?: false
        }
    }

    override suspend fun saveAccessToken(accessToken: String) {
        context.datastore.edit { prefs ->
            prefs[SharedPrefsKeys.ACCESS_TOKEN] = accessToken
        }
    }

    override suspend fun readAccessToken(): Flow<String> {
        return context.datastore.data.map { prefs ->
            prefs[SharedPrefsKeys.ACCESS_TOKEN] ?: ""
        }
    }

    override suspend fun saveLoggedInUser(user: User) {
        context.datastore.edit { prefs ->
            prefs[SharedPrefsKeys.USER_ID] = user.id
            prefs[SharedPrefsKeys.USER_NAME] = user.name
            prefs[SharedPrefsKeys.USER_EMAIL] = user.email
            prefs[SharedPrefsKeys.USER_ROLE] = user.role
            prefs[SharedPrefsKeys.USER_AVATAR] = user.avatar
            prefs[SharedPrefsKeys.USER_CREATED_AT] = user.creationAt
            prefs[SharedPrefsKeys.USER_UPDATED_AT] = user.updatedAt

        }
    }

    override suspend fun readLoggedInUser(): Flow<User> {

        return flow {
            var id = 0
            var name = ""
            var email = ""
            var role = ""
            var avatar = ""
            var createdAt = ""
            var updatedAt = ""

            context.datastore.data.map { prefs ->
                id = prefs[SharedPrefsKeys.USER_ID] ?: 0
                name = prefs[SharedPrefsKeys.USER_NAME] ?: ""
                email = prefs[SharedPrefsKeys.USER_EMAIL] ?: ""
                role = prefs[SharedPrefsKeys.USER_ROLE] ?: ""
                avatar = prefs[SharedPrefsKeys.USER_AVATAR] ?: ""
                createdAt = prefs[SharedPrefsKeys.USER_CREATED_AT] ?: ""
                updatedAt = prefs[SharedPrefsKeys.USER_UPDATED_AT] ?: ""
            }

            val user = User(
                id = id,
                name = name,
                email = email,
                role = role,
                avatar = avatar,
                creationAt = createdAt,
                updatedAt = updatedAt,
                password = ""
            )

            emit(user)
            return@flow
        }

    }

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "local_shared_prefs")

    private object SharedPrefsKeys {
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        val USER_ID = intPreferencesKey("USER_ID")
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val USER_ROLE = stringPreferencesKey("USER_ROLE")
        val USER_AVATAR = stringPreferencesKey("USER_AVATAR")
        val USER_CREATED_AT = stringPreferencesKey("USER_CREATED_AT")
        val USER_UPDATED_AT = stringPreferencesKey("USER_UPDATED_AT")
    }
}