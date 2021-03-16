package de.schnettler.datastore.compose.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.preferences.core.Preferences
import de.schnettler.datastore.compose.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.*
import de.schnettler.datastore.compose.ui.preference.*
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
        is SwitchPreference -> {
            SwitchPreferenceWidget(
                preference = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                }
            )
        }
        is ListPreference -> {
            ListPreferenceWidget(
                preference = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                })
        }
        is MultiSelectListPreference -> {
            MultiSelectListPreferenceWidget(
                preference = item,
                values = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValuesChange = { newValues ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValues) }
                }
            )
        }
        is SeekBarPreference -> {
            SeekBarPreferenceWidget(
                preference = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                },
            )
        }
        is DropDownMenuPreference -> {
            DropDownPreferenceWidget(
                preference = item,
                value = prefs?.get(item.request.key) ?: item.request.defaultValue,
                onValueChange = { newValue ->
                    scope.launch { dataStoreManager.editPreference(item.request.key, newValue) }
                }
            )
        }
        is TextPreference -> {
            TextPreferenceWidget(
                preference = item,
                onClick = item.onClick,
            )
        }
    }
}