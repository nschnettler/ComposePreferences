package de.schnettler.datastore.manager

import androidx.datastore.preferences.core.Preferences

interface PreferenceMetaData<T> {
    val key: String
    val defaultValue: T
    val keyProvider: (String) -> Preferences.Key<T>
    val dataStoreKey: Preferences.Key<T>
        get() = keyProvider(key)
}