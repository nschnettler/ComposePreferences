package de.schnettler.composepreferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import de.schnettler.datastore.manager.PreferenceRequest

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object SwitchPrefExample : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_switch"), true)
object DependencySwitchPrefExample : PreferenceRequest<Boolean>(booleanPreferencesKey("dependency_pref_switch"), true)
object ListPrefExample : PreferenceRequest<String>(stringPreferencesKey("pref_list"), "")
object MultiPrefExample : PreferenceRequest<Set<String>>(stringSetPreferencesKey("pref_multi_list"), emptySet())
object SeekPrefExample : PreferenceRequest<Float>(floatPreferencesKey("pref_seek"), 50F)
object DropDownPrefExample : PreferenceRequest<String>(stringPreferencesKey("pref_dropdown_list"), "")