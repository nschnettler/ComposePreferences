package de.schnettler.datastorepreferences

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PreferenceScreen(items: List<BasePreferenceItem>) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val prefs by context.dataStore.data.collectAsState(initial = null)

    LazyColumn {
        items(items = items){ item ->
            when (item) {
                is SwitchPreferenceItem -> {
                    SwitchPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch(Dispatchers.IO) {
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
                        values = prefs?.get(item.prefKey),
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
            }
        }
    }
}