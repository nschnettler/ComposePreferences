package de.schnettler.composepreferences

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
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
fun ListPreference(
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    entries: Map<String, String>,
    defaultValue: String = ""
) {
    val preferences = AmbientPreferences.current
    val selected by preferences.getString(key = key, defaultValue).asFlow().collectAsState(initial = defaultValue)
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }

    Preference(
        title = title,
        summary = entries[selected] ?: summary,
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
                Column {
                    entries.forEach { current ->
                        val isSelected = selected == current.key
                        val onSelected = {
                            preferences.sharedPreferences.edit().putString(key, current.key).apply()
                            closeDialog()
                        }
                        Row(Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = { if (!isSelected) onSelected() }
                            )
                            .padding(16.dp)
                        ) {
                            RadioButton(
                                selected = isSelected,
                                onClick = { if (!isSelected) onSelected() }
                            )
                            Text(
                                text = current.value,
                                style = MaterialTheme.typography.body1.merge(),
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = { }
        )
    }
}