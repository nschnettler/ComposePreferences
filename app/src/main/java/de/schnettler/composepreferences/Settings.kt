package de.schnettler.composepreferences

import androidx.datastore.preferences.core.*
import de.schnettler.datastore.manager.PreferenceRequest

object SwitchPrefExample : PreferenceRequest<Boolean>(booleanPreferencesKey("pref_switch"), true)
object ListPrefExample: PreferenceRequest<String>(stringPreferencesKey("pref_list"), "")
object MultiPrefExample: PreferenceRequest<Set<String>>(stringSetPreferencesKey("pref_multi_list"), emptySet())
object SeekPrefExample: PreferenceRequest<Float>(floatPreferencesKey("pref_seek"), 50F)
object DropDownPrefExample: PreferenceRequest<String>(stringPreferencesKey("pref_dropdown_list"), "")