package de.schnettler.composepreferences

import androidx.compose.Composable
import androidx.compose.collectAsState
import androidx.compose.getValue
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.size
import androidx.ui.material.ListItem
import androidx.ui.material.Switch
import androidx.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun SwitchPreference(
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    defaultValue: Boolean = false
) {
    val preferences = AmbientPreferences.current
    val state by preferences.getBoolean(key, defaultValue).asFlow().collectAsState(initial = defaultValue)
    ListItem(
        text = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
        secondaryText = { Text(text = summary) },
        icon = { Icon(asset = icon, modifier = Modifier.size(40.dp)) },
        trailing = {
            Switch(checked = state, onCheckedChange = {
                preferences.sharedPreferences.edit().putBoolean(key, it).apply()
            })
        },
        onClick = {
            preferences.sharedPreferences.edit().putBoolean(key, !state).apply()
        }
    )
}