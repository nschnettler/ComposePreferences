package de.schnettler.composepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@ExperimentalMaterialApi
@Composable
fun SwitchPreference(
    title: String,
    summary: String,
    singleLineTitle: Boolean,
    icon: ImageVector,
    value: Boolean,
    onValueChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    Preference(
        title = title,
        summary = summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { onValueChange(!value) }
    ) {
        Switch(checked = value, onCheckedChange = { onValueChange(it) }, enabled = enabled)
    }
}