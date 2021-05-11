package de.schnettler.composepreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import java.text.NumberFormat

@ExperimentalMaterialApi
@Composable
fun SeekBarPreference(
    title: String,
    summary: String,
    singleLineTitle: Boolean,
    icon: ImageVector,
    value: Float?,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    enabled: Boolean = true,
    valueRepresentation: (Float) -> String = { NumberFormat.getInstance().format(it) },
    onValueChange: (Float) -> Unit = {}
) {
    Preference(
        title = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
        summary = {
            SeekBarSummary(
                summary = summary,
                valueRepresentation = valueRepresentation,
                value = value,
                onValueChange = onValueChange,
                valueRange = valueRange,
                steps = steps,
                enabled = enabled,
            )
        },
        icon = icon,
        enabled = enabled,
    )
}

@Composable
private fun SeekBarSummary(
    summary: String,
    valueRepresentation: (Float) -> String,
    value: Float?,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    enabled: Boolean,
) {
    var sliderValue by remember(value == null) {
        mutableStateOf(value ?: 0f)
    }

    Column {
        Text(text = summary)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = valueRepresentation(sliderValue))
            Spacer(modifier = Modifier.width(16.dp))
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = {
                    if (enabled) onValueChange(sliderValue)
                }
            )
        }
    }
}