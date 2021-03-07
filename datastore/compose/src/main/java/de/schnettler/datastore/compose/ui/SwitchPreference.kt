package de.schnettler.datastore.compose.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import de.schnettler.datastore.compose.model.SwitchPreferenceItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun SwitchPreference(
    item: SwitchPreferenceItem,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    Preference(
        item = item,
        onClick = { onValueChanged(!value) }
    ) {
        Switch(
            checked = value,
            onCheckedChange = { onValueChanged(!value) },
            enabled = item.enabled
        )
    }
}