package de.schnettler.datastore.compose.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.preferences.core.Preferences
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem.*
import de.schnettler.datastore.compose.ui.*
import de.schnettler.datastore.compose.ui.preference.ListPreference
import de.schnettler.datastore.compose.ui.preference.MultiSelectListPreference
import de.schnettler.datastore.compose.ui.preference.SwitchPreference
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
internal fun PreferenceItem(
    item: PreferenceItem<*>,
    prefs: Preferences?,
    dataStoreManager: DataStoreManager
) {
    val scope = rememberCoroutineScope()

    when (item) {
        is SwitchPreferenceItem -> {
            SwitchPreference(
                item = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                }
            )
        }
        is RadioBoxListPreferenceItem -> {
            ListPreference(
                item = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                })
        }
        is CheckBoxListPreferenceItem -> {
            MultiSelectListPreference(
                item = item,
                values = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValuesChange = { newValues ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValues) }
                }
            )
        }
        is SeekBarPreferenceItem -> {
            SeekBarPreference(
                item = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                },
            )
        }
        is BasicPreferenceItem -> {
            BasicPreference(
                item = item,
                onClick = item.onClick,
            )
        }
    }
}