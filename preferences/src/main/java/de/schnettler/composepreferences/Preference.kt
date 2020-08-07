package de.schnettler.composepreferences

import androidx.compose.Composable
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.size
import androidx.ui.material.ListItem
import androidx.ui.unit.dp

@Composable
fun Preference(
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    onClick: () -> Unit
) {
    ListItem(
        text = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
        secondaryText = { Text(text = summary) },
        icon = { Icon(asset = icon, modifier = Modifier.size(40.dp)) },
        onClick = { onClick() }
    )
}