package de.schnettler.datastore.compose.material3.widget

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import de.schnettler.datastore.compose.material3.LocalPreferenceEnabledStatus
import de.schnettler.datastore.compose.material3.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.material3.StatusWrapper

@Composable
@ExperimentalMaterial3Api
internal fun TextPreferenceWidget(
    preference: PreferenceItem<*>,
    summary: String? = null,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && preference.enabled

    StatusWrapper(enabled = isEnabled) {
        ListItem(
            headlineText = {
                Text(
                    text = preference.title,
                    maxLines = if (preference.singleLineTitle) 1 else Int.MAX_VALUE
                )
            },
            supportingText = { Text(text = summary ?: preference.summary) },
            leadingContent = preference.icon,
            modifier = Modifier.clickable(
                onClick = onClick,
                enabled = isEnabled
            ),
            trailingContent = trailing,
        )
    }
}

@Composable
@ExperimentalMaterial3Api
fun TextPreferenceWidget(
    preference: PreferenceItem<*>,
    summary: @Composable () -> Unit,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && preference.enabled

    StatusWrapper(enabled = isEnabled) {
        ListItem(
            headlineText = {
                Text(
                    text = preference.title,
                    maxLines = if (preference.singleLineTitle) 1 else Int.MAX_VALUE
                )
            },
            supportingText = summary,
            leadingContent = preference.icon,
            modifier = Modifier.clickable(
                onClick = onClick,
                enabled = isEnabled
            ),
            trailingContent = trailing,
        )
    }
}