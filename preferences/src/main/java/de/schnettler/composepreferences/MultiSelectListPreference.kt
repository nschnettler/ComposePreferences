package de.schnettler.composepreferences

import androidx.compose.Composable
import androidx.compose.collectAsState
import androidx.compose.state
import androidx.compose.getValue
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.selection.selectable
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.AlertDialog
import androidx.ui.material.Checkbox
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TextButton
import androidx.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun MultiSelectListPreference(
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    entries: Map<String, String>,
    defaultValue: Set<String> = emptySet()
) {
    val preferences = AmbientPreferences.current
    val selected by preferences.getStringSet(key = key, defaultValue).asFlow()
        .collectAsState(initial = defaultValue)
    val showDialog = state { false }
    val closeDialog = { showDialog.value = false }
    val descripion = entries.filter { selected.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    Preference(
        title = title,
        summary = if (descripion.isNotBlank()) descripion else summary,
        key = key,
        singleLineTitle = singleLineTitle,
        icon = icon
    ) {
        showDialog.value = true
    }

    if (showDialog.value) {
        AlertDialog(
            onCloseRequest = { closeDialog() },
            title = { Text(text = title) },
            text = {
                entries.forEach {
                    val isSelected = selected.contains(it.key)
                    val onSelectionChanged = {
                        val result = when (!isSelected) {
                            true -> selected + it.key
                            false -> selected - it.key
                        }
                        preferences.sharedPreferences.edit().putStringSet(key, result).apply()
                    }
                    Box(
                        modifier = Modifier.selectable(
                            selected = isSelected,
                            onClick = { onSelectionChanged() }
                        ),
                        children = {
                            Box {
                                Row(Modifier.fillMaxWidth().padding(16.dp)) {
                                    Checkbox(checked = isSelected, onCheckedChange = {
                                        onSelectionChanged()
                                    })
                                    Text(
                                        text = it.value,
                                        style = MaterialTheme.typography.body1.merge(),
                                        modifier = Modifier.padding(start = 16.dp)
                                    )
                                }
                            }
                        }
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { closeDialog() },
                    contentColor = MaterialTheme.colors.secondary
                ) {
                    Text(text = "Select")
                }
            }
        )
    }
}