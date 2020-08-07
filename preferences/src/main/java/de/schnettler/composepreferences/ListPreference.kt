package de.schnettler.composepreferences

import androidx.compose.Composable
import androidx.compose.collectAsState
import androidx.compose.getValue
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.foundation.Box
import androidx.ui.foundation.Text
import androidx.ui.foundation.selection.selectable
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import androidx.ui.layout.padding
import androidx.ui.material.AlertDialog
import androidx.ui.material.MaterialTheme
import androidx.ui.material.RadioButton
import androidx.ui.material.RadioGroup
import androidx.ui.unit.dp
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