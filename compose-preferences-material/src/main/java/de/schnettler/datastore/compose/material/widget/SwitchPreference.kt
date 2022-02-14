package de.schnettler.datastore.compose.material.widget

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import de.schnettler.datastore.compose.material.model.Preference.PreferenceItem.SwitchPreference

@ExperimentalMaterialApi
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
            enabled = preference.enabled
        )
    }
}