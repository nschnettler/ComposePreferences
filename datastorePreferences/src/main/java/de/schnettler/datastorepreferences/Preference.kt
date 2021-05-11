package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


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
        title = item.title,
        summary = summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        enabled = item.enabled,
        onClick = onClick,
        trailing = trailing
    )
}