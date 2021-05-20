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
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import de.schnettler.datastore.compose.model.Preference.PreferenceItem.MultiSelectListPreference

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
internal fun MultiSelectListPreferenceWidget(
    preference: MultiSelectListPreference,
    values: Set<String>,
    onValuesChange: (Set<String>) -> Unit
) {
    val (isDialogShown, showDialog) = remember { mutableStateOf(false) }
    val description = preference.entries.filter { values.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    TextPreferenceWidget(
        preference = preference,
        summary = if (description.isNotBlank()) description else null,
        onClick = { showDialog(!isDialogShown) }
    )

    if (isDialogShown) {
        AlertDialog(
            onDismissRequest = { showDialog(!isDialogShown) },
            title = { Text(text = preference.title) },
            buttons = {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    preference.entries.forEach { current ->
                        val isSelected = values.contains(current.key)
                        val onSelectionChanged = {
                            val result = when (!isSelected) {
                                true -> values + current.key
                                false -> values - current.key
                            }
                            onValuesChange(result)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .selectable(
                                    selected = isSelected,
                                    onClick = { onSelectionChanged() }
                                )
                                .padding(16.dp)
                        ) {
                            Checkbox(
                                checked = isSelected,
                                onCheckedChange = { onSelectionChanged() }
                            )
                            Text(
                                text = current.value,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                    TextButton(
                        onClick = { showDialog(!isDialogShown) },
                        colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.secondary),
                        modifier = Modifier.align(alignment = Alignment.End)
                    ) {
                        Text(text = "Select")
                    }
                }
            },
            properties = DialogProperties(
                usePlatformDefaultWidth = true
            )
        )
    }
}