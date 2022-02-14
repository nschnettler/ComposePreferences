package de.schnettler.datastore.compose.material

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.datastore.preferences.core.Preferences
import de.schnettler.datastore.compose.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.DropDownMenuPreference
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.ListPreference
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.MultiSelectListPreference
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.SeekBarPreference
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.SwitchPreference
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.TextPreference
import de.schnettler.datastore.compose.material.widget.ListPreferenceWidget
import de.schnettler.datastore.compose.material.widget.MultiSelectListPreferenceWidget
import de.schnettler.datastore.compose.material.widget.SeekBarPreferenceWidget
import de.schnettler.datastore.compose.material.widget.SwitchPreferenceWidget
import de.schnettler.datastore.compose.material.widget.TextPreferenceWidget
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
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
                }
            )
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
                }
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