package de.schnettler.composepreferences

import androidx.compose.foundation.ContentColorAmbient
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.size
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp

@Composable
fun Preference(
    title: String,
    summary: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = PreferenceEnabledAmbient.current && enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
            secondaryText = { Text(text = summary) },
            icon = { Icon(asset = icon, modifier = Modifier.size(40.dp)) },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}

@Composable
fun Preference(
    title: @Composable () -> Unit,
    summary: @Composable () -> Unit,
    icon: VectorAsset,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = PreferenceEnabledAmbient.current && enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = title,
            secondaryText = summary,
            icon = { Icon(asset = icon, modifier = Modifier.size(40.dp)) },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }

}

@Composable
fun StatusWrapper(enabled: Boolean = true, content: @Composable () -> Unit) {
    val color = if (enabled) contentColor() else contentColor().copy(0.2f)
    Providers(ContentColorAmbient provides color) {
        content()
    }
}