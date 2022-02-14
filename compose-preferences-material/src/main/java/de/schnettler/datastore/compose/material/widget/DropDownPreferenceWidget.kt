package de.schnettler.datastore.compose.material

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.datastore.compose.material.widget.TextPreferenceWidget
import de.schnettler.datastore.compose.material.model.Preference.PreferenceItem.DropDownMenuPreference

@ExperimentalMaterialApi
@Composable
internal fun DropDownPreferenceWidget(
    preference: DropDownMenuPreference,
    value: String,
    onValueChange: (String) -> Unit
) {
    val (isExpanded, expand) = remember { mutableStateOf(false) }

    TextPreferenceWidget(
        preference = preference,
        summary = preference.entries[value],
        onClick = { expand(!isExpanded) }
    )

    Box(
        modifier = Modifier
            .padding(start = 64.dp)
    ) {
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { expand(!isExpanded) }
        ) {
            preference.entries.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onValueChange(item.key)
                        expand(!isExpanded)
                    }
                ) {
                    Text(
                        text = item.value,
                        style = MaterialTheme.typography.body1.merge()
                    )
                }
            }
        }
    }
}