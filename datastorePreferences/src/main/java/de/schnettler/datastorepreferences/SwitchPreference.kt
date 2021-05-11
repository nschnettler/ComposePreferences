package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
fun SwitchPreference(
    item: SwitchPreferenceItem,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    de.schnettler.composepreferences.SwitchPreference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        value = value,
        onValueChange = onValueChange
    )
}