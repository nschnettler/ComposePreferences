package de.schnettler.composepreferences

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.getValue
import androidx.compose.setValue
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.foundation.Icon
import androidx.ui.foundation.Text
import androidx.ui.graphics.vector.VectorAsset
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.layout.Spacer
import androidx.ui.layout.size
import androidx.ui.layout.width
import androidx.ui.material.ListItem
import androidx.ui.material.Slider
import androidx.ui.unit.dp
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