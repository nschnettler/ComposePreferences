package de.schnettler.datastore.compose.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.schnettler.datastore.compose.model.BasePreferenceItem.PreferenceItem.SeekBarPreferenceItem
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
internal fun SeekBarPreference(
    item: SeekBarPreferenceItem,
    value: Float,
    onValueChanged: (Float) -> Unit,
) {
    val currentValue = remember(value) { mutableStateOf(value) }
    Preference(
        item = item,
        summary = {
            PreferenceSummary(
                item = item,
                sliderValue = currentValue.value,
                onValueChanged = { currentValue.value = it },
                onValueChangeEnd = { onValueChanged(currentValue.value) }
            )
        },
    )
}

@Composable
private fun PreferenceSummary(
    item: SeekBarPreferenceItem,
    sliderValue: Float,
    onValueChanged: (Float) -> Unit,
    onValueChangeEnd: () -> Unit,
) {
    Column {
        Text(text = item.summary)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = item.valueRepresentation(sliderValue))
            Spacer(modifier = Modifier.width(16.dp))
            Slider(
                value = sliderValue,
                onValueChange = { if (item.enabled) onValueChanged(it) },
                valueRange = item.valueRange,
                steps = item.steps,
                onValueChangeFinished = onValueChangeEnd
            )
        }
    }
}