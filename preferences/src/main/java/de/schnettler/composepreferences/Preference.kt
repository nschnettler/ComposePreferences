package de.schnettler.composepreferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Preference(
    title: String,
    summary: String,
    singleLineTitle: Boolean,
    icon: ImageVector,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = AmbientPreferenceEnabled.current && enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
            secondaryText = { Text(text = summary) },
            icon = { Icon(imageVector = icon, null, modifier = Modifier.size(40.dp)) },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}

@Composable
fun Preference(
    title: @Composable () -> Unit,
    summary: @Composable () -> Unit,
    icon: ImageVector,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = AmbientPreferenceEnabled.current && enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = title,
            secondaryText = summary,
            icon = { Icon(imageVector = icon, null, modifier = Modifier.size(40.dp)) },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }

}

@Composable
fun StatusWrapper(enabled: Boolean = true, content: @Composable () -> Unit) {
    Providers(AmbientContentAlpha provides if (enabled) ContentAlpha.high else ContentAlpha.disabled) {
        content()
    }
}