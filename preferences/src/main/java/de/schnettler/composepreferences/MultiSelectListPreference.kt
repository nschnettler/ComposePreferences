package de.schnettler.composepreferences

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MultiSelectListPreference(
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: ImageVector,
    entries: Map<String, String>,
    defaultValue: Set<String> = emptySet(),
    enabled: Boolean = true,
) {
    val preferences = AmbientPreference.current
    val selected by preferences.getStringSet(key = key, defaultValue).asFlow()
        .collectAsState(initial = defaultValue)
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }
    val descripion = entries.filter { selected.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    Preference(
        title = title,
        summary = if (descripion.isNotBlank()) descripion else summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { closeDialog() },
            title = { Text(text = title) },
            text = {
                Column {
                    entries.forEach { current ->
                        val isSelected = selected.contains(current.key)
                        val onSelectionChanged = {
                            val result = when (!isSelected) {
                                true -> selected + current.key
                                false -> selected - current.key
                            }
                            preferences.sharedPreferences.edit().putStringSet(key, result).apply()
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