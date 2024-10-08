package com.dm.berxley.stylishstore.data.sharedprefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.dm.berxley.stylishstore.domain.sharedprefs.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {
    override suspend fun saveIsLoggedIn(isLoggedIn: Boolean) {
        context.datastore.edit {prefs->
            prefs[SharedPrefsKeys.IS_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun readIsLoggedIn(): Flow<Boolean> {
        return context.datastore.data.map {prefs->
            prefs[SharedPrefsKeys.IS_LOGGED_IN] ?: false
        }
    }

    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "local_shared_prefs")

    private object SharedPrefsKeys{
        val IS_LOGGED_IN = booleanPreferencesKey("IS_LOGGED_IN")
    }
}