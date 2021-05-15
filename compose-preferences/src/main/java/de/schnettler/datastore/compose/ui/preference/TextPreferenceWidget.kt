package de.schnettler.datastore.compose.ui.preference

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.datastore.compose.LocalPreferenceEnabledStatus
import de.schnettler.datastore.compose.model.Preference.PreferenceItem
import de.schnettler.datastore.compose.ui.StatusWrapper

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
            secondaryText = { Text(text = summary ?: preference.summary) },
            icon = {
                Icon(
                    imageVector = preference.icon,
                    null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            },
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
            icon = {
                Icon(
                    imageVector = preference.icon,
                    null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            },
            modifier = Modifier.clickable(onClick = { if (isEnabled) onClick() }),
            trailing = trailing,
        )
    }
}