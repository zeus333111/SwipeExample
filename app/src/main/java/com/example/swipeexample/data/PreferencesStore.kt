package com.example.swipeexample.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PreferencesStore (private val context: Context) : CoroutineScope by CoroutineScope(Dispatchers.IO) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "example_prefs")
    private val FLAG_KEY = booleanPreferencesKey("flag")

    fun setFlag(flag: Boolean) = launch {
        context.dataStore.edit {
            it[FLAG_KEY] = flag
        }
    }

    fun getFlag(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[FLAG_KEY] ?: false
        }
    }
}