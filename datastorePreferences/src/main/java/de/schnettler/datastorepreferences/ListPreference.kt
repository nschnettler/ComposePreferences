package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable


@ExperimentalMaterialApi
@Composable
fun ListPreference(
    item: SingleListPreferenceItem,
    value: String?,
    onValueChanged: (String) -> Unit
) {
    de.schnettler.composepreferences.ListPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChanged = onValueChanged,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries
    )
}