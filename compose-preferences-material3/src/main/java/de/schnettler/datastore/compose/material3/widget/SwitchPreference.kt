package de.schnettler.datastore.compose.material3.widget

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import de.schnettler.datastore.compose.material3.compat.Material3SwitchDefaults
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem.SwitchPreference

@Composable
internal fun SwitchPreferenceWidget(
    preference: SwitchPreference,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    TextPreferenceWidget(
        preference = preference,
        onClick = { onValueChange(!value) }
    ) {
        Switch(
            checked = value,
            onCheckedChange = { onValueChange(!value) },
            enabled = preference.enabled,
            colors = Material3SwitchDefaults.colors()
        )
    }
}