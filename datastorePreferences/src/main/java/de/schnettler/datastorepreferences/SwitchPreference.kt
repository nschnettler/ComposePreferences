package de.schnettler.datastorepreferences

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun SwitchPreference(
    item: SwitchPreferenceItem,
    value: Boolean?,
    onValueChanged: (Boolean) -> Unit
) {
    val currentValue = value ?: item.defaultValue
    Preference(
        item = item,
        onClick = { onValueChanged(!currentValue) }
    ) {
        Switch(
            checked = currentValue,
            onCheckedChange = { onValueChanged(!currentValue) },
            enabled = item.enabled
        )
    }
}