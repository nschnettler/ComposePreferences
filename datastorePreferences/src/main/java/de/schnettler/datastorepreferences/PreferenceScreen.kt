package de.schnettler.datastorepreferences

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@ExperimentalMaterialApi
@Composable
fun PreferenceScreen(items: List<BasePreferenceItem>) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val prefs by context.dataStore.data.collectAsState(initial = null)

    LazyColumn {
        items(items = items) { item ->
            when (item) {
                is StringPreferenceItem -> {
                    EditTextPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChange = { newValue ->
                            scope.launch {
                                context.dataStore.edit { it[item.prefKey] = newValue }
                            }
                        }
                    )
                }
                is SwitchPreferenceItem -> {
                    SwitchPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch {
                                context.dataStore.edit { it[item.prefKey] = newValue }
                            }
                        }
                    )
                }
                is SingleListPreferenceItem -> {
                    ListPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch { context.dataStore.edit { it[item.prefKey] = newValue } }
                        })
                }
                is MultiListPreferenceItem -> {
                    MultiSelectListPreference(
                        item = item,
                        values = prefs?.get(item.prefKey) ?: emptySet(),
                        onValuesChanged = { newValues ->
                            scope.launch { context.dataStore.edit { it[item.prefKey] = newValues } }
                        }
                    )
                }
                is SeekbarPreferenceItem -> {
                    SeekBarPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch {
                                context.dataStore.edit { it[item.prefKey] = newValue }
                            }
                        },
                    )
                }
                else -> throw IllegalStateException("Unsupported preference item")
            }
        }
    }
}