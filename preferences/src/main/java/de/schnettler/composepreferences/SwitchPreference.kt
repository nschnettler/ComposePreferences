package de.schnettler.composepreferences

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.size
import androidx.compose.material.ListItem
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
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