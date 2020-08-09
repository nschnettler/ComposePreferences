package de.schnettler.composepreferences

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ListItem
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.math.roundToInt

@ExperimentalCoroutinesApi
@Composable
fun SeekBarPreference(
    title: String,
    summary: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    key: String,
    defaultValue: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0
) {
    val preferences = AmbientPreferences.current
    var sliderValue by state { preferences.getFloat(key, defaultValue).get() }
    ListItem(
        text = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
        secondaryText = {
            Column {
                Text(text = summary)
                Row(verticalGravity = Alignment.CenterVertically) {
                    Text(text = "${sliderValue.roundToInt()} %")
                    Spacer(modifier = Modifier.width(16.dp))
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                        valueRange = valueRange,
                        steps = steps,
                        onValueChangeEnd = {
                            preferences.sharedPreferences.edit().putFloat(key, sliderValue).apply()
                        }
                    )
                }
            }
        },
        icon = { Icon(asset = icon, modifier = Modifier.size(40.dp)) }
    )
}