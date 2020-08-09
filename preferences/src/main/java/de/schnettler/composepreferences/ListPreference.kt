package de.schnettler.composepreferences

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.state
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
    val showDialog = state { false }
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
            onCloseRequest = { closeDialog() },
            title = { Text(text = title) },
            text = {
                RadioGroup {
                    entries.forEach {
                        val isSelected = selected == it.key
                        val onSelected = {
                            preferences.sharedPreferences.edit().putString(key, it.key).apply()
                            closeDialog()
                        }
                        Box(
                            modifier = Modifier.selectable(
                                selected = isSelected,
                                onClick = { if (!isSelected) onSelected() }
                            ),
                            children = {
                                Box {
                                    Row(Modifier.fillMaxWidth().padding(16.dp)) {
                                        RadioButton(selected = isSelected, onClick = onSelected)
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
                }
            },
            confirmButton = { }
        )
    }
}