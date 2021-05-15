package de.schnettler.datastore.compose.ui.preference

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem.CheckBoxListPreferenceItem
import de.schnettler.datastore.compose.ui.BasicPreference
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
internal fun MultiSelectListPreference(
    item: CheckBoxListPreferenceItem,
    values: Set<String>,
    onValuesChange: (Set<String>) -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }
    val description = item.entries.filter { values.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    BasicPreference(
        item = item,
        summary = if (description.isNotBlank()) description else null,
        onClick = { showDialog.value = true }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text(text = item.title) },
            text = {
                Column {
                    item.entries.forEach { current ->
                        val isSelected = values.contains(current.key)
                        val onSelectionChanged = {
                            val result = when (!isSelected) {
                                true -> values + current.key
                                false -> values - current.key
                            }
                            onValuesChange(result)
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
                    colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondary),
                ) {
                    Text(text = "Select")
                }
            }
        )
    }
}