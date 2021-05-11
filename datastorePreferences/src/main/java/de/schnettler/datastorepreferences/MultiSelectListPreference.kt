package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
fun MultiSelectListPreference(
    item: MultiListPreferenceItem,
    value: Set<String>,
    onValueChange: (Set<String>) -> Unit
) {
    de.schnettler.composepreferences.MultiSelectListPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries,
        onValueChange = onValueChange
    )
}