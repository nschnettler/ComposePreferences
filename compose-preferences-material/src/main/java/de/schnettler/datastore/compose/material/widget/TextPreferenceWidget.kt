package de.schnettler.datastore.compose.material.widget

import androidx.compose.foundation.clickable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.schnettler.datastore.compose.material.LocalPreferenceEnabledStatus
import de.schnettler.datastore.compose.material.StatusWrapper
import de.schnettler.datastore.compose.material.model.Preference.PreferenceItem

@ExperimentalMaterialApi
@Composable
internal fun TextPreferenceWidget(
    preference: PreferenceItem<*>,
    summary: String? = null,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && preference.enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = {
                Text(
                    text = preference.title,
                    maxLines = if (preference.singleLineTitle) 1 else Int.MAX_VALUE
                )
            },
            secondaryText = (summary ?: preference.summary)?.let { { Text(text = it) } },
            icon = preference.icon,
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun TextPreferenceWidget(
    preference: PreferenceItem<*>,
    summary: @Composable () -> Unit,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && preference.enabled
    StatusWrapper(enabled = isEnabled) {
        ListItem(
            text = {
                Text(
                    text = preference.title,
                    maxLines = if (preference.singleLineTitle) 1 else Int.MAX_VALUE
                )
            },
            secondaryText = summary,
            icon = preference.icon,
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}