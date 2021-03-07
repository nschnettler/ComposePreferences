package de.schnettler.datastore.compose

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.preferences.core.Preferences
import de.schnettler.datastore.compose.model.BasePreferenceItem
import de.schnettler.datastore.compose.ui.ListPreference
import de.schnettler.datastore.compose.ui.MultiSelectListPreference
import de.schnettler.datastore.compose.ui.SeekBarPreference
import de.schnettler.datastore.compose.ui.SwitchPreference
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun PreferenceItemEntry(item: BasePreferenceItem.PreferenceItem<*>, prefs: Preferences?) {
    val scope = rememberCoroutineScope()
    val dataStore = LocalDataStoreManager.current

    when (item) {
        is BasePreferenceItem.PreferenceItem.SwitchPreferenceItem -> {
            SwitchPreference(
                item = item,
                value = prefs?.get(item.metaData.dataStoreKey) ?: item.metaData.defaultValue,
                onValueChanged = { newValue ->
                    scope.launch() { dataStore.editPreference(item.metaData, newValue) }
                }
            )
        }
        is BasePreferenceItem.PreferenceItem.RadioBoxListPreferenceItem -> {
            ListPreference(
                item = item,
                value = prefs?.get(item.metaData.dataStoreKey) ?: item.metaData.defaultValue,
                onValueChanged = { newValue ->
                    scope.launch { dataStore.editPreference(item.metaData, newValue) }
                })
        }
        is BasePreferenceItem.PreferenceItem.CheckBoxListPreferenceItem -> {
            MultiSelectListPreference(
                item = item,
                values = prefs?.get(item.metaData.dataStoreKey) ?: item.metaData.defaultValue,
                onValuesChanged = { newValues ->
                    scope.launch { dataStore.editPreference(item.metaData, newValues) }
                }
            )
        }
        is BasePreferenceItem.PreferenceItem.SeekBarPreferenceItem -> {
            SeekBarPreference(
                item = item,
                value = prefs?.get(item.metaData.dataStoreKey) ?: item.metaData.defaultValue,
                onValueChanged = { newValue ->
                    scope.launch { dataStore.editPreference(item.metaData, newValue) }
                },
            )
        }
    }
}