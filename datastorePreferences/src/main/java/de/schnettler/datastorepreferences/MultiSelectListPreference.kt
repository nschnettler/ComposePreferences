package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
fun MultiSelectListPreference(
    item: MultiListPreferenceItem,
    values: Set<String>,
    onValuesChanged: (Set<String>) -> Unit
) {
    de.schnettler.composepreferences.MultiSelectListPreference(
        title = item.title,
        summary = item.summary,
        values = values,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries,
        onValuesChanged = onValuesChanged
    )
}