package de.schnettler.datastore.compose.ui.preference

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem.SwitchPreferenceItem
import de.schnettler.datastore.compose.ui.BasicPreference
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
internal fun SwitchPreference(
    item: SwitchPreferenceItem,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    BasicPreference(
        item = item,
        onClick = { onValueChange(!value) }
    ) {
        Switch(
            checked = value,
            onCheckedChange = { onValueChange(!value) },
            enabled = item.enabled
        )
    }
}