package de.schnettler.datastore.compose.material3.widget

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.schnettler.datastore.compose.material3.LocalPreferenceEnabledStatus
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.material3.StatusWrapper

@Composable
internal fun TextPreferenceWidget(
    preference: PreferenceItem<*>,
    summary: String? = null,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && preference.enabled
    StatusWrapper(enabled = isEnabled) {
        MaterialListItem(
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

@Composable
fun TextPreferenceWidget(
    preference: PreferenceItem<*>,
    summary: @Composable () -> Unit,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && preference.enabled
    StatusWrapper(enabled = isEnabled) {
        MaterialListItem(
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