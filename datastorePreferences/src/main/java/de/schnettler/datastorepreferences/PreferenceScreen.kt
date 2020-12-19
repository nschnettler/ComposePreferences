package de.schnettler.datastorepreferences

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.runtime.*
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun PreferenceScreen(context: Context, items: List<BasePreferenceItem>) {
    val scope = rememberCoroutineScope()
    val dataStore: DataStore<Preferences> = remember { context.createDataStore(name = "settings") }

    val prefs by dataStore.data.collectAsState(initial = null)

    LazyColumn {
        items(items = items, itemContent = { item ->
            when (item) {
                is SwitchPreferenceItem -> {
                    SwitchPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch(Dispatchers.IO) {
                                dataStore.edit { it[item.prefKey] = newValue }
                            }
                        }
                    )
                }
                is SingleListPreferenceItem -> {
                    ListPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch { dataStore.edit { it[item.prefKey] = newValue } }
                        })
                }
                is MultiListPreferenceItem -> {
                    MultiSelectListPreference(
                        item = item,
                        values = prefs?.get(item.prefKey),
                        onValuesChanged = { newValues ->
                            scope.launch { dataStore.edit { it[item.prefKey] = newValues } }
                        }
                    )
                }
                is SeekbarPreferenceItem -> {
                    SeekBarPreference(
                        item = item,
                        value = prefs?.get(item.prefKey),
                        onValueChanged = { newValue ->
                            scope.launch {
                                dataStore.edit { it[item.prefKey] = newValue }
                            }
                        },
                    )
                }
            }
        })
    }
}