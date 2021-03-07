package de.schnettler.datastore.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import de.schnettler.datastore.compose.model.*
import de.schnettler.datastore.compose.ui.ListPreference
import de.schnettler.datastore.compose.ui.SeekBarPreference
import de.schnettler.datastore.compose.ui.SwitchPreference
import de.schnettler.datastorepreferences.LocalDataStoreManager
import de.schnettler.datastorepreferences.MultiSelectListPreference
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PreferenceScreen(
    items: List<BasePreferenceItem>,
    modifier: Modifier = Modifier,
    statusBarPadding: Boolean = false,
) {
    val scope = rememberCoroutineScope()
    val dataStore = LocalDataStoreManager.current

    val prefs by dataStore.preferenceFlow.collectAsState(initial = null)

    LazyColumn(modifier = modifier) {
        if (statusBarPadding) {
            item { Spacer(modifier = Modifier.statusBarsPadding()) }
        }

        items(items = items){ item ->
            when (item) {
                is SwitchPreferenceItem -> {
                    SwitchPreference(
                        item = item,
                        value = prefs?.get(item.entry.dataStoreKey) ?: item.entry.defaultValue,
                        onValueChanged = { newValue ->
                            scope.launch() { dataStore.editPreference(item.entry, newValue) }
                        }
                    )
                }
                is SingleListPreferenceItem -> {
                    ListPreference(
                        item = item,
                        value = prefs?.get(item.entry.dataStoreKey) ?: item.entry.defaultValue,
                        onValueChanged = { newValue ->
                            scope.launch { dataStore.editPreference(item.entry, newValue) }
                        })
                }
                is MultiListPreferenceItem -> {
                    MultiSelectListPreference(
                        item = item,
                        values = prefs?.get(item.entry.dataStoreKey) ?: item.entry.defaultValue,
                        onValuesChanged = { newValues ->
                            scope.launch { dataStore.editPreference(item.entry, newValues) }
                        }
                    )
                }
                is SeekbarPreferenceItem -> {
                    SeekBarPreference(
                        item = item,
                        value = prefs?.get(item.entry.dataStoreKey) ?: item.entry.defaultValue,
                        onValueChanged = { newValue ->
                            scope.launch { dataStore.editPreference(item.entry, newValue) }
                        },
                    )
                }
            }
        }
    }
}