package de.schnettler.composepreferences

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
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
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    entries: Map<String, String>,
    defaultValue: Set<String> = emptySet()
) {
    val preferences = PreferenceAmbient.current
    val selected by preferences.getStringSet(key = key, defaultValue).asFlow()
        .collectAsState(initial = defaultValue)
    val showDialog = remember { mutableStateOf(false) }
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
            onDismissRequest = { closeDialog() },
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