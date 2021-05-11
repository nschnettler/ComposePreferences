package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable


@ExperimentalMaterialApi
@Composable
fun ListPreference(
    item: SingleListPreferenceItem,
    value: String?,
    onValueChange: (String) -> Unit
) {
    de.schnettler.composepreferences.ListPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChange = onValueChange,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries
    )
}