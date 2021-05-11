package de.schnettler.datastorepreferences

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.composepreferences.StatusWrapper


@ExperimentalMaterialApi
@Composable
fun Preference(
    item: PreferenceItem<*>,
    summary: String? = null,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    Preference(
        item = item,
        summary = { Text(text = summary ?: item.summary) },
        onClick = onClick,
        trailing = trailing
    )
}

@ExperimentalMaterialApi
@Composable
fun Preference(
    item: PreferenceItem<*>,
    summary: @Composable () -> Unit,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    de.schnettler.composepreferences.Preference(
        title = { Text(text = item.title, maxLines = if (item.singleLineTitle) 1 else Int.MAX_VALUE) },
        summary = summary,
        icon = item.icon,
        enabled = item.enabled,
        onClick = onClick,
        trailing = trailing
    )
}