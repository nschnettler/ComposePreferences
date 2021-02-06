package de.schnettler.datastorepreferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Preference(
    item: PreferenceItem<*>,
    summary: String? = null,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    StatusWrapper(enabled = item.enabled) {
        ListItem(
            text = { Text(text = item.title, maxLines = if (item.singleLineTitle) 1 else Int.MAX_VALUE) },
            secondaryText = { Text(text = summary ?: item.summary) },
            icon = { Icon(imageVector = item.icon, null, modifier = Modifier.size(40.dp)) },
            modifier = Modifier.clickable(onClick = { if (item.enabled) onClick() }),
            trailing = trailing,
        )
    }
}

@Composable
fun Preference(
    item: PreferenceItem<*>,
    summary: @Composable () -> Unit,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    StatusWrapper(enabled = item.enabled) {
        ListItem(
            text = { Text(text = item.title, maxLines = if (item.singleLineTitle) 1 else Int.MAX_VALUE) },
            secondaryText = summary,
            icon = { Icon(imageVector = item.icon, null, modifier = Modifier.size(40.dp)) },
            modifier = Modifier.clickable(onClick = { if (item.enabled) onClick() }),
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