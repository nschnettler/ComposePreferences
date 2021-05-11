package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
fun SeekBarPreference(
    item: SeekbarPreferenceItem,
    value: Float?,
    onValueChange: (Float) -> Unit,
) {
    de.schnettler.composepreferences.SeekBarPreference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        value = value,
        onValueChange = onValueChange
    )
}