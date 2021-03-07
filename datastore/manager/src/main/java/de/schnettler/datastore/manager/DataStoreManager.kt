package de.schnettler.datastore.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DataStoreManager(appContext: Context) {

    val settingsDataStore: DataStore<Preferences> = appContext.dataStore

    val preferenceFlow = settingsDataStore.data

    suspend fun <T> getPreference(preferenceEntry: PreferenceMetaData<T>) =
        preferenceFlow.first()[preferenceEntry.dataStoreKey] ?: preferenceEntry.defaultValue

    fun <T> getPreferenceFlow(preferenceEntry: PreferenceMetaData<T>) =
        preferenceFlow.map {
            it[preferenceEntry.dataStoreKey] ?: preferenceEntry.defaultValue
        }

    suspend fun <T> editPreference(preference: PreferenceMetaData<T>, newValue: T) {
        settingsDataStore.edit { preferences ->
            preferences[preference.dataStoreKey] = newValue
        }
    }
}