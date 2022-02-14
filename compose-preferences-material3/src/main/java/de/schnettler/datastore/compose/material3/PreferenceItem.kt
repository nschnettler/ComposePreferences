package de.schnettler.datastore.compose.material3

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.datastore.preferences.core.Preferences
import de.schnettler.datastore.compose.material3.widget.DropDownPreferenceWidget
import de.schnettler.datastore.compose.material3.widget.ListPreferenceWidget
import de.schnettler.datastore.compose.material3.widget.MultiSelectListPreferenceWidget
import de.schnettler.datastore.compose.material3.widget.SeekBarPreferenceWidget
import de.schnettler.datastore.compose.material3.widget.SwitchPreferenceWidget
import de.schnettler.datastore.compose.material3.widget.TextPreferenceWidget
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.DropDownMenuPreference
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.ListPreference
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.MultiSelectListPreference
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.SeekBarPreference
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.SwitchPreference
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.TextPreference
import de.schnettler.datastore.manager.DataStoreManager
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
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