package de.schnettler.composepreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun MultiSelectListPreference(
    title: String,
    summary: String,
    values: Set<String>,
    onValuesChanged: (Set<String>) -> Unit = {},
    singleLineTitle: Boolean,
    icon: ImageVector,
    entries: Map<String, String>,
    enabled: Boolean = true,
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }
    val description = entries.filter { values.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    Preference(
        title = title,
        summary = if (description.isNotBlank()) description else summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true }
    )

    if (showDialog.value) {
        var selectedValues by remember(values) { mutableStateOf(values) }
        PreferenceDialog(
            onDismissRequest = { closeDialog() },
            title = title,
            onConfirm = {
                onValuesChanged(selectedValues)
                closeDialog()
            },
        ) {
            Column {
                entries.forEach { current ->
                    val isSelected = selectedValues.contains(current.key)
                    val onSelectionChanged = {
                        selectedValues = when (!isSelected) {
                            true -> selectedValues + current.key
                            false -> selectedValues - current.key
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = onSelectionChanged
                            )
                            .padding(16.dp)
                    ) {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = {
                                onSelectionChanged()
                            }
                        )
                        Text(
                            text = current.value,
                            style = MaterialTheme.typography.body1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}