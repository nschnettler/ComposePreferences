package de.schnettler.composepreferences

import androidx.datastore.preferences.core.*
import de.schnettler.datastore.manager.PreferenceMetaData

sealed class Settings<T>(
    override val key: String,
    override val defaultValue: T,
    override val keyProvider: (String) -> Preferences.Key<T>,
) : PreferenceMetaData<T> {
    object SwitchPrefExample : Settings<Boolean>("pref_switch", true, ::booleanPreferencesKey)
    object ListPrefExample: Settings<String>("pref_list", "", ::stringPreferencesKey)
    object MultiPrefExample: Settings<Set<String>>("pref_multi_list", emptySet(), ::stringSetPreferencesKey)
    object SeekPrefExample: Settings<Float>("pref_seek", 50F, ::floatPreferencesKey)
}