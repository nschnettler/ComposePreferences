package de.schnettler.datastorepreferences

import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MultiSelectListPreference(
    item: MultiListPreferenceItem,
    values: Set<String>?,
    onValuesChanged: (Set<String>) -> Unit
) {
    val selectedValues = values ?: item.defaultValue
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }
    val descripion = item.entries.filter { selectedValues.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    Preference(
        item = item,
        summary = if (descripion.isNotBlank()) descripion else null,
        onClick = { showDialog.value = true }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text(text = item.title) },
            text = {
                Column {
                    item.entries.forEach { current ->
                        val isSelected = selectedValues.contains(current.key)
                        val onSelectionChanged = {
                            val result = when (!isSelected) {
                                true -> selectedValues + current.key
                                false -> selectedValues - current.key
                            }
                            onValuesChanged(result)
                        }
                        Row(Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = { onSelectionChanged() }
                            )
                            .padding(16.dp)
                        ) {
                            Checkbox(checked = isSelected, onCheckedChange = {
                                onSelectionChanged()
                            })
                            Text(
                                text = current.value,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { closeDialog() },
                    colors = ButtonConstants.defaultTextButtonColors(contentColor = MaterialTheme.colors.secondary),
                ) {
                    Text(text = "Select")
                }
            }
        )
    }
}