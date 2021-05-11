package de.schnettler.composepreferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
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
    Preference(
        title = title,
        summary = { Text(text = summary) },
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = onClick,
        trailing = trailing
    )
}

@ExperimentalMaterialApi
@Composable
fun Preference(
    title: String,
    summary: @Composable () -> Unit,
    singleLineTitle: Boolean,
    icon: ImageVector,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
            secondaryText = summary,
            icon = { Icon(imageVector = icon, null, modifier = Modifier
                .padding(8.dp)
                .size(24.dp)) },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }

}

@Composable
fun StatusWrapper(enabled: Boolean = true, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalContentAlpha provides if (enabled) ContentAlpha.high else ContentAlpha.disabled) {
        content()
    }
}

val LocalPreferenceEnabledStatus: ProvidableCompositionLocal<Boolean> =
    compositionLocalOf(structuralEqualityPolicy()) { true }