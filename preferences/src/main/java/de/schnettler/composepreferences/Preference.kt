package de.schnettler.composepreferences

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.AmbientEmphasisLevels
import androidx.compose.material.ListItem
import androidx.compose.material.ProvideEmphasis
import androidx.compose.runtime.Composable
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
    val isEnabled = AmbientPreferenceEnabled.current && enabled
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
    val isEnabled = AmbientPreferenceEnabled.current && enabled
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
    ProvideEmphasis(emphasis = if (enabled) AmbientEmphasisLevels.current.high else AmbientEmphasisLevels.current.disabled) {
        content()
    }
}